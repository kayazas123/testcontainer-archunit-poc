package com.hardik.wrestleverse.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hardik.wrestleverse.entity.Wrestler;
import com.hardik.wrestleverse.request.WresterCreationRequest;
import com.hardik.wrestleverse.request.WrestlerUpdationRequest;
import com.hardik.wrestleverse.service.WrestlerService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WrestlerController {

	private final WrestlerService wrestlerService;

	@GetMapping("v1/wrestler/{wresterId}")
	public Wrestler getWrestlerByIdHandler(@PathVariable(name = "wrestlerId", required = true) final UUID wrestlerId) {
		return wrestlerService.getWrestlerById(wrestlerId);
	}

	@GetMapping("v1/wrestler-in-company/{companyId}")
	public List<Wrestler> getWrestlersInCompanyIdHandler(
			@PathVariable(name = "companyId", required = true) final UUID companyId) {
		return wrestlerService.getWrestersByCompanyId(companyId);
	}

	@GetMapping("v1/wrestler")
	public List<Wrestler> getWrestlersHandler() {
		return wrestlerService.getWrestlers();
	}
	
	@PostMapping("v1/wrestler")
	public Wrestler wrestlerCreationHandler(@RequestBody final WresterCreationRequest wresterCreationRequest ) {
		return wrestlerService.createWrestler(wresterCreationRequest);
	}
	
	@PutMapping("v1/wrestler")
	public Wrestler wrestlerUpdationHandler(@RequestBody final WrestlerUpdationRequest wrestlerUpdationRequest) {
		return wrestlerService.updateWrestler(wrestlerUpdationRequest);
	}

}
