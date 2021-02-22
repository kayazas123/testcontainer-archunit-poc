package com.hardik.wrestleverse.request;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class PromoterCreationRequest {
	
	private final String firstName;
	private final String lastName;
	private final LocalDate dateOfBirth;
	private final String gender;
	private final String status;
	private final UUID companyId;

}
