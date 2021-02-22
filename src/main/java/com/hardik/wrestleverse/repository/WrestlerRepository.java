package com.hardik.wrestleverse.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hardik.wrestleverse.entity.Wrestler;

@Repository
public interface WrestlerRepository extends JpaRepository<Wrestler, UUID>{
	
	List<Wrestler> findByCompanyId(UUID companyId);

}
