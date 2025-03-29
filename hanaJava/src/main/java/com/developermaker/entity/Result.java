package com.developermaker.entity;

import java.util.Map;

public class Result {
    private final String message;
    private Map<ScoreType, Integer> scoreMap;

    public Result(String message) {
        this.message = message;
    }

    public Result(String message, Map<ScoreType, Integer> scoreMap) {
        this.message = message;
        this.scoreMap = scoreMap;
    }

    public String getMessage() {
        return message;
    }

    public Map<ScoreType, Integer> getScoreMap() {
        return scoreMap;
    }
}