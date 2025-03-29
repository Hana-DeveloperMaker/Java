package com.developermaker.entity;

import java.util.Map;

public class Result {
    private String imgName;
    private String message;
    private Map<ScoreType, Integer> scores;

    public Result() {}

    public Result(String imgName, String message) {
        this.imgName = imgName;
        this.message = message;
    }

    public Result(String imgName, String message, Map<ScoreType, Integer> scores) {
        this.imgName = imgName;
        this.message = message;
        this.scores = scores;
    }

    public String getImgName() { return imgName; }
    public String getMessage() { return message; }
    public Map<ScoreType, Integer> getScores() { return scores; }
}