package com.hardik.wrestleverse.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hardik.wrestleverse.test.container.PostgresqlTestContainer;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestcontainerPocApplicationTests extends PostgresqlTestContainer{

	@Test
	void contextLoads() {
	}

}
