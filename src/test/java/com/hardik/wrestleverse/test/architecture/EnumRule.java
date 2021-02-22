package com.hardik.wrestleverse.test.architecture;

import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hardik.wrestleverse.entity.Company;
import com.hardik.wrestleverse.enums.Status;
import com.hardik.wrestleverse.test.container.PostgresqlTestContainer;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class EnumRule extends PostgresqlTestContainer{

	private final JavaClasses enumClasses = new ClassFileImporter().importPackagesOf(Status.class);

	@Test
	public void all_classes_in_enums_package_should_be_enum() {
		var evalautedClasses = classes().should().beEnums().evaluate(enumClasses);
		assertFalse(evalautedClasses.hasViolation());
		log.info(enumClasses.size() + " Classes Successfully Passes Enum Type Check");
	}

}
