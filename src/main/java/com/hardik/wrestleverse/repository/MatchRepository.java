package com.hardik.wrestleverse.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hardik.wrestleverse.entity.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, UUID>{

	List<Match> findByCompanyId(UUID companyId);
}
