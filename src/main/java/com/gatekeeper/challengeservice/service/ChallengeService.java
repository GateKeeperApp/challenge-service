package com.gatekeeper.challengeservice.service;

import com.gatekeeper.challengeservice.model.ChallengeDto;
import com.gatekeeper.challengeservice.model.SolutionDto;

public interface ChallengeService {

    /**
     * Initializes a challenge by generating two random integers.
     *
     * @return a challenge DTO
     */
    ChallengeDto initializeChallenge();

    /**
     * Verifies the solution.
     *
     * @param id          identifies a challenge data record
     * @param solutionDto a solution DTO
     * @return a boolean if the solution value is correct
     */
    boolean verifySolution(long id, SolutionDto solutionDto);
}
