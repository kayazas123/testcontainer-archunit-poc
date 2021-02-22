package com.hardik.wrestleverse.utility;

import java.util.Random;

import com.hardik.wrestleverse.enums.Outcome;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OutcomeUtility {

	public Outcome getOutcome() {
		switch (new Random().nextInt(3)) {
		case 0:
			return Outcome.WRESTLER_ONE_WINS;
		case 1:
			return Outcome.WRESTLER_TWO_WINS;
		default:
			return Outcome.NO_OUTCOME;
		}
	}

}
