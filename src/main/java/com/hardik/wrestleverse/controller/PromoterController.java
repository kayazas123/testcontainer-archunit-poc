package com.hardik.wrestleverse.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hardik.wrestleverse.entity.Promoter;
import com.hardik.wrestleverse.request.PromoterCreationRequest;
import com.hardik.wrestleverse.request.PromoterUpdationRequest;
import com.hardik.wrestleverse.service.PromoterService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PromoterController {

	private final PromoterService promoterService;

	@GetMapping("v1/promoter/{promoterId}")
	public Promoter getPromoterByIdHandler(@PathVariable(name = "promoter_id", required = true) final UUID promoterId) {
		return promoterService.getPromoterById(promoterId);
	}
	
	@GetMapping("v1/promoter")
	public List<Promoter> getPromotersHandler() {
		return promoterService.getPromoters();
	}
	
	@PostMapping("v1/promoter")
	public Promoter promoterCreationHandler(@RequestBody final PromoterCreationRequest promoterCreationRequest) {
		return promoterService.createPromoter(promoterCreationRequest);
	}
	
	@PutMapping("v1/promoter")
	public Promoter promoterUpdationHandler(@RequestBody final PromoterUpdationRequest promoterUpdationRequest) {
		return promoterService.updatePromoter(promoterUpdationRequest);
	}
}
