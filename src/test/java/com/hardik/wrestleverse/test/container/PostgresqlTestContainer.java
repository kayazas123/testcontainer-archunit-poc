package com.hardik.wrestleverse.test.container;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class PostgresqlTestContainer {


	public static PostgreSQLContainer container;

	static {
		container = (PostgreSQLContainer) new PostgreSQLContainer()
				.withUsername("wrestleverse-test")
				.withPassword("wrestleverse-test")
				.withDatabaseName("wrestleverse-test")
				.withReuse(true);
		if (!(container.isCreated() && container.isRunning()))
			container.start();
	}

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.password", container::getPassword);
		registry.add("spring.datasource.username", container::getUsername);
	}
}