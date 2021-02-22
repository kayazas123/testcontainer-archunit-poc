package com.hardik.wrestleverse.test.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hardik.wrestleverse.service.CompanyService;
import com.hardik.wrestleverse.test.container.PostgresqlTestContainer;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class ServiceRule extends PostgresqlTestContainer{

	private final JavaClasses serviceClasses = new ClassFileImporter().importPackagesOf(CompanyService.class);

	@Test
	public void service_classes_should_only_depend_on_repositories_or_utility_classes() {
		var evaluatedClasses = classes().should().dependOnClassesThat().areAnnotatedWith(Repository.class).orShould()
				.dependOnClassesThat().resideInAPackage("com.hardik.wrestleverse.test.utility").evaluate(serviceClasses);
		assertFalse(evaluatedClasses.hasViolation());
		log.info(serviceClasses.size() + " Service Classes Successfully Passed Dependency Level Check");
	}

	@Test
	public void all_service_classes_to_be_annotated_with_service_annotation() {
		var evaluatedClasses = classes().should().beAnnotatedWith(Service.class).evaluate(serviceClasses);
		assertFalse(evaluatedClasses.hasViolation());
		log.info(serviceClasses.size() + " Service Classes Successfully Passed @Service Annotation Check");
	}

	@Test
	public void all_service_classes_be_prefixed_with_service() {
		var evaluatedClasses = classes().should().haveSimpleNameEndingWith("Service").evaluate(serviceClasses);
		assertFalse(evaluatedClasses.hasViolation());
		log.info(serviceClasses.size() + " Service Classes Successfully Passed Naming Conventions Check");
	}

}
