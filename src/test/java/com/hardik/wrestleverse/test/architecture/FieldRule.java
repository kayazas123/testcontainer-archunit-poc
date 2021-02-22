package com.hardik.wrestleverse.test.architecture;

import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hardik.wrestleverse.controller.CompanyController;
import com.hardik.wrestleverse.repository.CompanyRepository;
import com.hardik.wrestleverse.service.CompanyService;
import com.hardik.wrestleverse.test.container.PostgresqlTestContainer;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;


import lombok.extern.slf4j.Slf4j;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class FieldRule extends PostgresqlTestContainer{

	private final JavaClasses classesInQuestion = new ClassFileImporter().importPackagesOf(CompanyController.class,
			CompanyService.class, CompanyRepository.class);

	@Test
	public void all_fields_should_be_private_and_final() {
		var evaluatedClasses = fields().should().bePrivate().andShould().beFinal()
				.because("This Is The Convention We Are Following").evaluate(classesInQuestion);
		assertFalse(evaluatedClasses.hasViolation());
		log.info(classesInQuestion.size() + " Classes Successfully Evaluated For Private Final Field Rule");
	}

	@Test
	public void all_fields_should_be_injected_through_constructor() {
		var evaluatedClasses = NO_CLASSES_SHOULD_USE_FIELD_INJECTION
				.because("Autowiring Through Constructor Should Be Done").evaluate(classesInQuestion);
		assertFalse(evaluatedClasses.hasViolation());
		log.info(classesInQuestion.size() + " Classes Successfully Evaluated For Constructor Injection Evaluation");
	}

}
