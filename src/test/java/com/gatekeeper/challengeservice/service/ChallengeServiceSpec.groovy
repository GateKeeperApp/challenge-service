package com.gatekeeper.challengeservice.service

import com.gatekeeper.challengeservice.model.ChallengeDto
import com.gatekeeper.challengeservice.model.ChallengeInfo
import com.gatekeeper.challengeservice.model.SolutionDto
import com.gatekeeper.challengeservice.repository.ChallengeInfoRepository
import com.gatekeeper.challengeservice.service.impl.ChallengeServiceImpl
import spock.lang.Specification

class ChallengeServiceSpec extends Specification {

    ChallengeInfoRepository challengeInfoRepository = Mock(ChallengeInfoRepository)
    ChallengeService challengeService = new ChallengeServiceImpl(
            challengeInfoRepository
    )

    def "Test initializeChallenge"() {
        given:
        ChallengeInfo challengeInfo = new ChallengeInfo(
                id: 1L
        )

        when:
        ChallengeDto challengeDto = challengeService.initializeChallenge()

        then:
        1 * challengeInfoRepository.save(_ as ChallengeInfo) >> challengeInfo
        0 * _

        and:
        challengeDto.id == challengeInfo.id
        challengeDto.values
    }

    def "Test verifySolution"() {
        given:
        Long id = 1L
        int value = 10
        SolutionDto solutionDto = new SolutionDto(
                value: value
        )
        ChallengeInfo challengeInfo = new ChallengeInfo(
                id: id,
                value: value
        )

        when:
        boolean result = challengeService.verifySolution(id, solutionDto)

        then:
        1 * challengeInfoRepository.findOne(id) >> challengeInfo
        0 * _

        and:
        result
    }
}
