package com.hardik.wrestleverse.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hardik.wrestleverse.entity.Match;
import com.hardik.wrestleverse.entity.Wrestler;
import com.hardik.wrestleverse.exception.InvalidMatchIdException;
import com.hardik.wrestleverse.exception.InvalidWrestlerIdException;
import com.hardik.wrestleverse.exception.WrestlerNotFromSameCompanyException;
import com.hardik.wrestleverse.repository.MatchRepository;
import com.hardik.wrestleverse.repository.WrestlerRepository;
import com.hardik.wrestleverse.request.MatchCreationRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MatchService {

	private final MatchRepository matchRepository;

	private final WrestlerRepository wrestlerRepository;

	public Match getMatchById(UUID matchId) {
		return matchRepository.findById(matchId).orElseThrow(() -> new InvalidMatchIdException());
	}

	public List<Match> getMatchesByCompanyId(UUID companyId) {
		return matchRepository.findByCompanyId(companyId);
	}

	public Match createMatch(MatchCreationRequest matchCreationRequest) {
		Wrestler wrestlerOne = wrestlerRepository.findById(matchCreationRequest.getWrestlerOneId())
				.orElseThrow(() -> new InvalidWrestlerIdException());
		Wrestler wrestlerTwo = wrestlerRepository.findById(matchCreationRequest.getWrestlerTwoId())
				.orElseThrow(() -> new InvalidWrestlerIdException());
		
		if (!wrestlerOne.getCompanyId().equals(wrestlerTwo.getCompanyId()))
			throw new WrestlerNotFromSameCompanyException();

		var match = new Match();
		match.setWrestlerOneId(matchCreationRequest.getWrestlerOneId());
		match.setWresterTwoId(matchCreationRequest.getWrestlerTwoId());
		match.setCompanyId(wrestlerOne.getCompanyId());
		return matchRepository.save(match);
	}

}
