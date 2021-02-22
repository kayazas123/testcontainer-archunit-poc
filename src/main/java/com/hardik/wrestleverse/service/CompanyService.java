package com.hardik.wrestleverse.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hardik.wrestleverse.entity.Company;
import com.hardik.wrestleverse.exception.InvalidCompanyIdException;
import com.hardik.wrestleverse.repository.CompanyRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompanyService {

	private final CompanyRepository companyRepository;

	public Company getCompanyById(UUID companyId) {
		return companyRepository.findById(companyId).orElseThrow(() -> new InvalidCompanyIdException());
	}

	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

}
