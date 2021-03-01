package com.hardik.wrestleverse.test.container;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class RedisTestContainer {

	public static GenericContainer container;

	static {
		container = new GenericContainer(DockerImageName.parse("redis:5.0.3-alpine")).withExposedPorts(6379)
				.withReuse(true);
		if (!(container.isCreated() && container.isRunning()))
			container.start();
	}

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.redis.host", container::getHost);
		registry.add("spring.redis.port", container::getFirstMappedPort);
	}
}