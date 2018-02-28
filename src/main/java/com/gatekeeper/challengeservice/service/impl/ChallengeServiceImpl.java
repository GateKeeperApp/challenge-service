package com.gatekeeper.challengeservice.service.impl;

import com.gatekeeper.challengeservice.model.ChallengeDto;
import com.gatekeeper.challengeservice.model.ChallengeInfo;
import com.gatekeeper.challengeservice.model.SolutionDto;
import com.gatekeeper.challengeservice.repository.ChallengeInfoRepository;
import com.gatekeeper.challengeservice.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    private static final long CHALLENGE_DATA_STREAM_SIZE = 10L;
    private static final int RANDOM_NUMBER_ORIGIN = -99999;
    private static final int RANDOM_NUMBER_BOUND = 100000;

    private final ChallengeInfoRepository challengeInfoRepository;

    @Autowired
    public ChallengeServiceImpl(ChallengeInfoRepository challengeInfoRepository) {
        this.challengeInfoRepository = challengeInfoRepository;
    }

    @Override
    public ChallengeDto initializeChallenge() {
        IntStream challengeDataIntStream = new Random().ints(CHALLENGE_DATA_STREAM_SIZE, RANDOM_NUMBER_ORIGIN, RANDOM_NUMBER_BOUND);
        List<Integer> challengeData = challengeDataIntStream.boxed().collect(Collectors.toList());
        int sum = challengeData.stream().mapToInt(Integer::intValue).sum();
        ChallengeInfo challengeInfo = new ChallengeInfo();
        challengeInfo.setValue(sum);
        ChallengeInfo challengeInfoAdded = challengeInfoRepository.save(challengeInfo);
        ChallengeDto challengeDto = new ChallengeDto();
        challengeDto.setId(challengeInfoAdded.getId());
        challengeDto.setValues(challengeData);
        return challengeDto;
    }

    @Override
    public boolean verifySolution(final long id, final SolutionDto solutionDto) {
        ChallengeInfo challengeInfo = challengeInfoRepository.findOne(id);
        return (solutionDto.getValue() == challengeInfo.getValue());
    }
}
