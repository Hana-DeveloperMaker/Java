package com.developermaker.entity;

import java.util.EnumMap;
import java.util.Map;

public class User {
    private String nickname;
    private Map<ScoreType, Integer> scores;

    public User() {}

    public User(String nickname) {
        this.nickname = nickname;
        this.scores = new EnumMap<>(ScoreType.class);
        initScores();
    }

    private void initScores() {
        for (ScoreType type : ScoreType.values()) {
            scores.put(type, 0); // 기본 점수 0으로 초기화
        }
    }

    public String getNickname() { return nickname; }
    public Map<ScoreType, Integer> getScores() {
        return scores;
    }

    public void setNickname(String nickname) { this.nickname = nickname; }
    public void setScores(Map<ScoreType, Integer> scores) {
        // 새로운 Map을 받아와 기존 EnumMap에 반영
        this.scores = new EnumMap<>(ScoreType.class);
        for (ScoreType type : ScoreType.values()) {
            this.scores.put(type, scores.getOrDefault(type, 0));
        }
    }
    public void updateScore(ScoreType type, int score) {
        if (scores == null) {
            scores = new EnumMap<>(ScoreType.class);
            initScores();
        }
        scores.put(type, scores.getOrDefault(type, 0) + score);
    }

    @Override
    public String toString() {
        return "User{nickname='" + nickname + "'}";
    }
}

