package com.gatekeeper.challengeservice.repository;

import com.gatekeeper.challengeservice.model.ChallengeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeInfoRepository extends JpaRepository<ChallengeInfo, Long> {
}
