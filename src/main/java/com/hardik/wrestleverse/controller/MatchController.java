package com.hardik.wrestleverse.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hardik.wrestleverse.entity.Match;
import com.hardik.wrestleverse.request.MatchCreationRequest;
import com.hardik.wrestleverse.service.MatchService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MatchController {

	private final MatchService matchService;

	@GetMapping("v1/match/{matchId}")
	public Match getMatchByIdHandler(@PathVariable(name = "matchId", required = true) final UUID matchId) {
		return matchService.getMatchById(matchId);
	}
	
	@GetMapping("v1/match-in-company/{companyId}")
	public List<Match> getMatchesByCompanyIdHandler(@PathVariable(name = "companyId", required = true) final UUID companyId) {
		return matchService.getMatchesByCompanyId(companyId);
	}
	
	@PostMapping("v1/match")
	public Match matchCreationHandler(@RequestBody final MatchCreationRequest matchCreationRequest) {
		return matchService.createMatch(matchCreationRequest);
	}

}
