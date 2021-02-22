package com.hardik.wrestleverse.test.architecture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hardik.wrestleverse.entity.Company;
import com.hardik.wrestleverse.test.container.PostgresqlTestContainer;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

import lombok.extern.slf4j.Slf4j;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields;
import static org.junit.Assert.assertFalse;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class EntityRule extends PostgresqlTestContainer{

	private final JavaClasses entityClasses = new ClassFileImporter().importPackagesOf(Company.class);

	@Test
	public void entity_classes_should_be_annotated_with_entity_annotation() {
		var evaluatedClasses = classes().should().beAnnotatedWith(Entity.class)
				.because("Every Entity Class Should Be Annotated WIth @Entity").evaluate(entityClasses);
		assertFalse(evaluatedClasses.hasViolation());
		log.info(entityClasses.size() + " Classes Successfully Passed @javax.persistence.Entity Annotation Check");
	}

	@Test
	public void entity_classes_should_be_annotated_with_table_annotation() {
		var evaluatedClasses = classes().should().beAnnotatedWith(Table.class).because(
				"Every Entity Class Should Be Annotated WIth @Table With The Table Written In Plural In Arguments")
				.evaluate(entityClasses);
		assertFalse(evaluatedClasses.hasViolation());
		log.info(entityClasses.size() + " Classes Successfully Passed @javax.persistence.Table Annotation Check");
	}

	@Test
	public void entity_classes_should_implement_serializable_class() {
		var evaluatedClasses = classes().should().implement(Serializable.class).because("").evaluate(entityClasses);
		assertFalse(evaluatedClasses.hasViolation());
		log.info(entityClasses.size() + " Classes Successfully Passed Serializable Implementation Check");
	}

	@Test
	public void entity_classes_should_declare_serialSersionUID() {
		var evaluatedClasses = fields().that().haveName("serialVersionUID").should().beFinal().andShould().bePrivate()
				.andShould().beStatic().andShould().haveRawType(Long.TYPE)
				.because("Every Entity Class Should Declare A Private Static Final Long SerialVersionUID")
				.evaluate(entityClasses);
		assertFalse(evaluatedClasses.hasViolation());
		log.info(entityClasses.size() + " Classes Successfully Declare private static final long serialVersionUid");
	}

	@Test
	public void entity_classes_should_have_private_fields() {
		var evaluatedClasses = fields().should().bePrivate().evaluate(entityClasses);
		assertFalse(evaluatedClasses.hasViolation());
		log.info(entityClasses.size() + " Classes Successfully Passed Private Fields Check");
	}

	@Test
	public void entity_classes_should_not_have_date_type_fields() {
		var evaluatedClasses = noFields().should().haveRawType(Date.class).orShould().haveRawType(java.sql.Date.class)
				.because("java.util.Date and java.sql.Date are useless").evaluate(entityClasses);
		assertFalse(evaluatedClasses.hasViolation());
		log.info(entityClasses.size() + " Classes Successfully Passed Date Field Check");
	}

	@Test
	public void updatedAt_and_createdAt_fields_should_be_of_type_localDateTime() {
		var evaluatedClasses = fields().that().haveName("createdAt").or().haveName("updatedAt").or()
				.haveName("occuredOn").should().haveRawType(LocalDateTime.class)
				.because("LocalDateTime Is The Best Date Type In Java").evaluate(entityClasses);
		assertFalse(evaluatedClasses.hasViolation());
		log.info(entityClasses.size() + " Classes Successfully Passed LocalDateTime Type Check");
	}

	@Test
	public void primary_keys_should_be_either_of_type_uuid_or_integer() {
		var evaluatedClasses = fields().that().areAnnotatedWith(Id.class).should().haveRawType(UUID.class).orShould()
				.haveRawType(Integer.class)
				.because(
						"Entities To Be Exposed Should Have Primary Key UUID and Entities Not To Be Exposed Should Have Integer Type")
				.evaluate(entityClasses);
		assertFalse(evaluatedClasses.hasViolation());
		log.info(entityClasses.size() + " Classes Successfully Passed UUID//Integer Primary Key Type Check");
	}

}
