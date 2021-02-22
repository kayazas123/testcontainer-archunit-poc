package com.hardik.wrestleverse.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hardik.wrestleverse.entity.Company;
import com.hardik.wrestleverse.service.CompanyService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CompanyController {

	private final CompanyService companyService;

	@GetMapping("v1/company/{companyId}")
	public Company getCompanyByIdHandler(@PathVariable(name = "companyId", required = true) final UUID companyId) {
		return companyService.getCompanyById(companyId);
	}
	
	@GetMapping("v1/company")
	public List<Company> getCompaniesHandler(){
		return companyService.getAllCompanies();
	}

}
