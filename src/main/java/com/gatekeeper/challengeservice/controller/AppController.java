package com.gatekeeper.challengeservice.controller;

import com.gatekeeper.challengeservice.model.ChallengeDto;
import com.gatekeeper.challengeservice.model.SolutionDto;
import com.gatekeeper.challengeservice.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class AppController {

    private final ChallengeService challengeService;

    @Autowired
    public AppController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("ALIVE");
    }

    @RequestMapping(value = "/challenge", method = RequestMethod.GET)
    public ResponseEntity<ChallengeDto> getChallengeData() {
        return ResponseEntity.ok(challengeService.initializeChallenge());
    }

    @RequestMapping(value = "/challenge/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> verifySolution(@PathVariable long id, @RequestBody SolutionDto solutionDto) {
        boolean valid = challengeService.verifySolution(id, solutionDto);
        if (valid) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(id).toUri();
            return ResponseEntity.created(location)
                    .body("Success!");
        } else {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed");
        }
    }
}
