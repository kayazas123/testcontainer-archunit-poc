package com.hardik.wrestleverse.test.architecture;

import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hardik.wrestleverse.entity.Company;
import com.hardik.wrestleverse.repository.CompanyRepository;
import com.hardik.wrestleverse.test.container.PostgresqlTestContainer;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class RepositoryRule extends PostgresqlTestContainer{

	private final JavaClasses repositories = new ClassFileImporter().importPackagesOf(CompanyRepository.class);

	@Test
	public void repository_package_should_only_contain_interfaces() {
		var evaluatedRepositories = classes().should().beInterfaces()
				.because("Repository Package Should Only Contain Interfaces").evaluate(repositories);
		assertFalse(evaluatedRepositories.hasViolation());
		log.info(repositories.size() + " Interfaces Successfully Passed Interface Check");
	}

	@Test
	public void all_repositories_should_be_annotated_with_repository_annotation() {
		var evaluatedRepositories = classes().should().beAnnotatedWith(Repository.class).evaluate(repositories);
		assertFalse(evaluatedRepositories.hasViolation());
		log.info(repositories.size() + " Interfaces Successfully Passed @Repository Annotation Check");
	}

	@Test
	public void all_repositories_be_prefixed_with_repository() {
		var evaluatedRepositories = classes().should().haveSimpleNameEndingWith("Repository").evaluate(repositories);
		assertFalse(evaluatedRepositories.hasViolation());
		log.info(repositories.size() + " Interfaces Successfully Passed Naming Convention Checks");
	}

}
