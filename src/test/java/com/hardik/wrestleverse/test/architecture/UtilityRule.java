package com.hardik.wrestleverse.test.architecture;

import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hardik.wrestleverse.test.container.PostgresqlTestContainer;
import com.hardik.wrestleverse.utility.OutcomeUtility;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class UtilityRule extends PostgresqlTestContainer{

	private final JavaClasses utilityClasses = new ClassFileImporter().importPackagesOf(OutcomeUtility.class);

	@Test
	public void utility_classes_should_have_only_static_methods() {
		var evaluatedClasses = methods().should().beStatic().because("Utility Classes Should Have Static Methods")
				.evaluate(utilityClasses);
		assertFalse(evaluatedClasses.hasViolation());
		log.info(utilityClasses.size() + " Classes Successfully Passed Static Methods Check");
	}

}
