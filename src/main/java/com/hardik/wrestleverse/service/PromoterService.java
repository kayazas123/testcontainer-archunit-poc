package com.hardik.wrestleverse.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hardik.wrestleverse.entity.Promoter;
import com.hardik.wrestleverse.enums.Gender;
import com.hardik.wrestleverse.enums.Status;
import com.hardik.wrestleverse.exception.InvalidPromoterIdException;
import com.hardik.wrestleverse.repository.PromoterRepository;
import com.hardik.wrestleverse.request.PromoterCreationRequest;
import com.hardik.wrestleverse.request.PromoterUpdationRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PromoterService {

	private final PromoterRepository promoterRepository;

	public Promoter getPromoterById(UUID promoterId) {
		return promoterRepository.findById(promoterId).orElseThrow(() -> new InvalidPromoterIdException());
	}

	public List<Promoter> getPromoters() {
		return promoterRepository.findAll();
	}

	public Promoter createPromoter(PromoterCreationRequest promoterCreationRequest) {
		var promoter = new Promoter();
		promoter.setCompanyId(promoterCreationRequest.getCompanyId());
		promoter.setDateOfBirth(promoterCreationRequest.getDateOfBirth());
		promoter.setFirstName(promoterCreationRequest.getFirstName());
		promoter.setLastName(promoterCreationRequest.getLastName());
		promoter.setGender(promoterCreationRequest.getGender().equalsIgnoreCase("MALE") ? Gender.MALE : Gender.FEMALE);
		promoter.setStatus(
				promoterCreationRequest.getStatus().equalsIgnoreCase("ACTIVE") ? Status.ACTIVE : Status.RETIRED);
		return promoterRepository.save(promoter);
	}

	public Promoter updatePromoter(PromoterUpdationRequest promoterUpdationRequest) {
		var promoter = getPromoterById(promoterUpdationRequest.getPromoterId());
		promoter.setCompanyId(promoterUpdationRequest.getCompanyId());
		promoter.setDateOfBirth(promoterUpdationRequest.getDateOfBirth());
		promoter.setFirstName(promoterUpdationRequest.getFirstName());
		promoter.setLastName(promoterUpdationRequest.getLastName());
		promoter.setGender(promoterUpdationRequest.getGender().equalsIgnoreCase("MALE") ? Gender.MALE : Gender.FEMALE);
		promoter.setStatus(
				promoterUpdationRequest.getStatus().equalsIgnoreCase("ACTIVE") ? Status.ACTIVE : Status.RETIRED);
		return promoterRepository.save(promoter);
	}

}
