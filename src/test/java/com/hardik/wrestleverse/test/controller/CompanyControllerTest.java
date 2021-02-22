package com.hardik.wrestleverse.test.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hardik.wrestleverse.controller.CompanyController;
import com.hardik.wrestleverse.entity.Company;
import com.hardik.wrestleverse.repository.CompanyRepository;
import com.hardik.wrestleverse.test.container.PostgresqlTestContainer;
import com.hardik.wrestleverse.test.utility.ObjectFactory;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CompanyControllerTest extends PostgresqlTestContainer {

	private final CompanyController companyController;

	private final CompanyRepository companyRepository;

	private final EntityManager entityManager;
	
	private Company company;
	
	private List<UUID> persistedCompanies;
	
	@BeforeEach
	void setUp() {
		company = ObjectFactory.getCompany();
		persistedCompanies = new ArrayList<UUID>();
	}

	@Autowired
	public CompanyControllerTest(final CompanyController companyController, final CompanyRepository companyRepository,
			final EntityManager entityManager) {
		super();
		this.companyController = companyController;
		this.companyRepository = companyRepository;
		this.entityManager = entityManager;
	}

	@Test
	void getCompaniesHandler_method_returns_all_companies_in_the_database_test() {
		assertAll(() -> assertThat(companyRepository.count()).isNotZero(),
				() -> assertThat(companyController.getCompaniesHandler().size()).isEqualTo(companyRepository.count()));
	}

	@Test
	void getCompanyByIdHandler_returns_compnay_with_correct_values_test() throws InterruptedException {
		var savedCompany = companyRepository.save(company);
		persistedCompanies.add(savedCompany.getId());
		var retrievedCompany = companyController.getCompanyByIdHandler(savedCompany.getId());
		assertAll(() -> assertThat(retrievedCompany.getId()).isEqualTo(savedCompany.getId()),
				() -> assertThat(retrievedCompany.getName()).isEqualTo(savedCompany.getName()),
				() -> assertThat(retrievedCompany.getFoundedOn()).isEqualTo(savedCompany.getFoundedOn()));
	}
	
	@AfterEach
	void cleanUp() {
		entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=0;");
		persistedCompanies.forEach(persistedCompany -> companyRepository.deleteById(persistedCompany));
		entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=1;");
	}
}
