package com.hardik.wrestleverse.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hardik.wrestleverse.entity.Promoter;

@Repository
public interface PromoterRepository extends JpaRepository<Promoter, UUID>{

}
