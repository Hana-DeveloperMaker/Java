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
    public void setScores(Map<ScoreType, Integer> scores) { this.scores = scores; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Result other)) return false;
        return imgName.equals(other.imgName)
                && message.equals(other.message)
                && scores.equals(other.scores);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(imgName, message, scores);
    }
}