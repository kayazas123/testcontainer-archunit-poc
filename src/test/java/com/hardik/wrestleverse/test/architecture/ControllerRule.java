package com.hardik.wrestleverse.test.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.RestController;

import com.hardik.wrestleverse.controller.CompanyController;
import com.hardik.wrestleverse.test.container.PostgresqlTestContainer;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class ControllerRule extends PostgresqlTestContainer{

	private final JavaClasses controllerClasses = new ClassFileImporter().importPackagesOf(CompanyController.class);

	@Test
	public void controller_classes_should_depend_only_on_service_layer() {
		var evaluatedClasses = classes().should().dependOnClassesThat().areAnnotatedWith(Service.class)
				.evaluate(controllerClasses);
		assertFalse(evaluatedClasses.hasViolation());
		log.info(controllerClasses.size() + " Controller Classes Successfully Passed Dependency Level Check");
	}

	@Test
	public void all_controlller_classes_to_be_annotated_with_controller_annotation_or_restController_annotation() {
		var evaluatedClasses = classes().should().beAnnotatedWith(Controller.class).orShould()
				.beAnnotatedWith(RestController.class).evaluate(controllerClasses);
		assertFalse(evaluatedClasses.hasViolation());
		log.info(controllerClasses.size() + " Controller Classes Successfully Passed @Controller Annotation Check");
	}

	@Test
	public void all_controller_classes_be_prefixed_with_controller() {
		var evaluatedClasses = classes().should().haveSimpleNameEndingWith("Controller").evaluate(controllerClasses);
		assertFalse(evaluatedClasses.hasViolation());
		log.info(controllerClasses.size() + " Controller Classes Successfully Passed Naming Conventions Check");
	}

}
