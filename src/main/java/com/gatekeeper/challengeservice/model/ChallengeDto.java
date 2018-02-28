package com.gatekeeper.challengeservice.model;

import java.util.List;

public class ChallengeDto {

    private Long id;
    private List<Integer> values;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }
}
