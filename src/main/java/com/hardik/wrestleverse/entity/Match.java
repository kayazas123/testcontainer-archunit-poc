package com.hardik.wrestleverse.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.hardik.wrestleverse.enums.Outcome;
import com.hardik.wrestleverse.utility.OutcomeUtility;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "matches")
@Getter
@Setter
public class Match implements Serializable{

	private static final long serialVersionUID = -1018336894293870905L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private UUID id;

	@Column(name = "company_id")
	private UUID companyId;

	@Hidden
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", nullable = false, insertable = false, updatable = false)
	private Company company;
	
	@Column(name = "wrestler_one")
	private UUID wrestlerOneId;

	@Hidden
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "wrestler_one", nullable = false, insertable = false, updatable = false)
	private Wrestler wresterOne;
	
	@Column(name = "wrestler_two")
	private UUID wresterTwoId;

	@Hidden
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "wrestler_two", nullable = false, insertable = false, updatable = false)
	private Wrestler wrestlerTwo;
	
	@Column(name = "outcome", nullable = false)
	@Enumerated(EnumType.STRING)
	private Outcome outcome;
	
	@Column(name = "occured_on", nullable = false, unique = true)
	private LocalDateTime occuredOn;
	
	@PrePersist
	public void onCreate() {
		this.outcome = OutcomeUtility.getOutcome();
		this.occuredOn = LocalDateTime.now();
	}

}
