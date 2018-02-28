package com.gatekeeper.challengeservice.controller

import com.gatekeeper.challengeservice.model.ChallengeDto
import com.gatekeeper.challengeservice.model.SolutionDto
import com.gatekeeper.challengeservice.service.ChallengeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

class AppControllerSpec extends Specification {

    ChallengeService challengeService = Mock(ChallengeService)
    AppController appController = new AppController(
            challengeService
    )

    def "Test index"() {
        when:
        ResponseEntity<String> response = appController.index()

        then:
        0 * _

        and:
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == "ALIVE"
    }

    def "Test getChallengeData"() {
        given:
        ChallengeDto challengeDto = new ChallengeDto(
                id: 1L,
                values: [1, 3, 5, 7, 9]
        )

        when:
        ResponseEntity<ChallengeDto> response = appController.getChallengeData()

        then:
        1 * challengeService.initializeChallenge() >> challengeDto
        0 * _

        and:
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == challengeDto
    }

    // TODO: fix spec
    @Ignore
    @Unroll
    def "Test verifySolution"() {
        given:
        Long id = 1L
        SolutionDto solutionDto = new SolutionDto(
                value: 1
        )

        when:
        ResponseEntity<?> response = appController.verifySolution(id, solutionDto)

        then:
        1 * challengeService.verifySolution(id, solutionDto) >> result
        0 * _

        and:
        response.getStatusCode() == statusCode

        where:
        result | statusCode
        true   | HttpStatus.CREATED
        false  | HttpStatus.UNPROCESSABLE_ENTITY
    }
}
