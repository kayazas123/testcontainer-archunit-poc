package com.hardik.wrestleverse.test.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hardik.wrestleverse.controller.WrestlerController;
import com.hardik.wrestleverse.repository.CompanyRepository;
import com.hardik.wrestleverse.test.container.PostgresqlTestContainer;
import com.hardik.wrestleverse.test.container.RedisTestContainer;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WrestlerControllerTest extends PostgresqlTestContainer {

	private final WrestlerController wrestlerController;

	private final CompanyRepository companyRepository;

	@Autowired
	public WrestlerControllerTest(final WrestlerController wrestlerController,
			final CompanyRepository companyRepository) {
		super();
		this.wrestlerController = wrestlerController;
		this.companyRepository = companyRepository;
	}

	@Nested
	public class WrestlerControllerCacheTest extends RedisTestContainer {

		@Test
		public void data_comes_from_cache_instead_of_database() {
			wrestlerController
					.getWrestlersInCompanyIdHandler(companyRepository.findAll().stream().findAny().get().getId());
		}
	}

}
