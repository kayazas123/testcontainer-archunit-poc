package com.hardik.wrestleverse.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hardik.wrestleverse.entity.Wrestler;
import com.hardik.wrestleverse.enums.Gender;
import com.hardik.wrestleverse.enums.Status;
import com.hardik.wrestleverse.exception.InvalidWrestlerIdException;
import com.hardik.wrestleverse.repository.WrestlerRepository;
import com.hardik.wrestleverse.request.WresterCreationRequest;
import com.hardik.wrestleverse.request.WrestlerUpdationRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WrestlerService {

	private final WrestlerRepository wrestlerRepository;

	public Wrestler getWrestlerById(UUID wrestlerId) {
		return wrestlerRepository.findById(wrestlerId).orElseThrow(() -> new InvalidWrestlerIdException());
	}

	public List<Wrestler> getWrestersByCompanyId(UUID companyId) {
		return wrestlerRepository.findByCompanyId(companyId);
	}

	public List<Wrestler> getWrestlers() {
		return wrestlerRepository.findAll();
	}

	public Wrestler createWrestler(WresterCreationRequest wresterCreationRequest) {
		var wrestler = new Wrestler();
		wrestler.setCompanyId(wresterCreationRequest.getCompanyId());
		wrestler.setFirstName(wresterCreationRequest.getFirstName());
		wrestler.setLastName(wresterCreationRequest.getStageName());
		wrestler.setStageName(wresterCreationRequest.getStageName());
		wrestler.setDateOfBirth(wresterCreationRequest.getDateOfBirth());
		wrestler.setGender(wresterCreationRequest.getGender().equalsIgnoreCase("MALE") ? Gender.MALE : Gender.FEMALE);
		wrestler.setStatus(
				wresterCreationRequest.getStatus().equalsIgnoreCase("ACTIVE") ? Status.ACTIVE : Status.RETIRED);
		return wrestlerRepository.save(wrestler);
	}

	public Wrestler updateWrestler(WrestlerUpdationRequest wrestlerUpdationRequest) {
		var wrestler = getWrestlerById(wrestlerUpdationRequest.getWrestlerId());
		wrestler.setCompanyId(wrestlerUpdationRequest.getCompanyId());
		wrestler.setFirstName(wrestlerUpdationRequest.getFirstName());
		wrestler.setLastName(wrestlerUpdationRequest.getStageName());
		wrestler.setStageName(wrestlerUpdationRequest.getStageName());
		wrestler.setDateOfBirth(wrestlerUpdationRequest.getDateOfBirth());
		wrestler.setGender(wrestlerUpdationRequest.getGender().equalsIgnoreCase("MALE") ? Gender.MALE : Gender.FEMALE);
		wrestler.setStatus(
				wrestlerUpdationRequest.getStatus().equalsIgnoreCase("ACTIVE") ? Status.ACTIVE : Status.RETIRED);
		return wrestlerRepository.save(wrestler);
	}
}
