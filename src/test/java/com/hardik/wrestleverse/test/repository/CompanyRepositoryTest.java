package com.hardik.wrestleverse.test.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hardik.wrestleverse.entity.Company;
import com.hardik.wrestleverse.repository.CompanyRepository;
import com.hardik.wrestleverse.test.container.PostgresqlTestContainer;
import com.hardik.wrestleverse.test.utility.ObjectFactory;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CompanyRepositoryTest extends PostgresqlTestContainer {

	private final CompanyRepository companyRepository;

	private final EntityManager entityManager;

	private Company company;

	private List<UUID> persistedCompanies;

	@Autowired
	public CompanyRepositoryTest(CompanyRepository companyRepository, EntityManager entityManager) {
		super();
		this.companyRepository = companyRepository;
		this.entityManager = entityManager;
	}

	@BeforeEach
	void setUp() {
		company = ObjectFactory.getCompany();
		persistedCompanies = new ArrayList<UUID>();
	}

	@Test
	void saving_company_with_duplicate_name_throws_exception_test() {
		assertThatThrownBy(() -> {
			var savedCompany = companyRepository.save(company);
			persistedCompanies.add(savedCompany.getId());
			var duplicateCompany = SerializationUtils.clone(savedCompany);
			entityManager.flush();
			companyRepository.save(duplicateCompany);
		}).isInstanceOf(Exception.class);
	}

	@Test
	void id_assigned_by_database_trigger__on_save_test() {
		var savedCompany = companyRepository.save(company);
		persistedCompanies.add(savedCompany.getId());
		assertAll(() -> assertThat(savedCompany.getId()).isNotNull(),
				() -> assertThat(savedCompany.getId()).isInstanceOf(UUID.class));
	}

	@Test
	void company_is_being_saved_test() {
		var companiesBeforeInsertion = companyRepository.count();
		var savedCompany = companyRepository.save(company);
		persistedCompanies.add(savedCompany.getId());
		assertThat(companyRepository.count()).isEqualTo(companiesBeforeInsertion + 1);
	}

	@AfterEach
	void cleanUp() {
		entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=0;");
		persistedCompanies.forEach(persistedCompany -> companyRepository.deleteById(persistedCompany));
		entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=1;");
	}

}
