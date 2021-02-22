package com.hardik.wrestleverse.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode.Exclude;

@Entity
@Table(name = "companies")
@Getter
@Setter
public class Company implements Serializable{

	private static final long serialVersionUID = -5858158444572875469L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private UUID id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	@Column(name = "founded_on", nullable = false)
	private LocalDate foundedOn;
	
	@Hidden
	@Exclude
	@JsonIgnore
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
	private Set<Wrestler> wrestlers;

	@Hidden
	@Exclude
	@JsonIgnore
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
	private Set<Promoter> promoters;
	
	@Hidden
	@Exclude
	@JsonIgnore
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
	private Set<Match> matches;
	
	@Column(name = "created_at", nullable = false, insertable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
	private LocalDateTime updatedAt;

}