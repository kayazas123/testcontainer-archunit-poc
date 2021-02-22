package com.hardik.wrestleverse.test.utility;

import java.time.LocalDate;

import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils;

import com.hardik.wrestleverse.entity.Company;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ObjectFactory {
	
	public Company getCompany() {
		var company = new Company();
		company.setFoundedOn(LocalDate.now());
		company.setName(RandomStringUtils.random(14));
		return company;
	}
}
