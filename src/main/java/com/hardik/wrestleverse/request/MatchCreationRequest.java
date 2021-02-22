package com.hardik.wrestleverse.request;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class MatchCreationRequest {
	
	private final UUID wrestlerOneId;
	private final UUID wrestlerTwoId;

}
