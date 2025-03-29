package com.developermaker.entity;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class User {
    private String nickname;
    private Map<ScoreType, Integer> scores;
    private List<Result> scoreList;

    public User() {}

    public User(String nickname) {
        this.nickname = nickname;
        this.scores = new EnumMap<>(ScoreType.class);
        this.scoreList = new ArrayList<>();
        initScores();
    }

    private void initScores() {
        for (ScoreType type : ScoreType.values()) {
            scores.put(type, 0); // 기본 점수 0으로 초기화
        }
    }

    public String getNickname() { return nickname; }
    public Map<ScoreType, Integer> getScores() { return scores; }
    public List<Result> getScoreList() { return scoreList; }

    public void setNickname(String nickname) { this.nickname = nickname; }
    public void setScores(Map<ScoreType, Integer> scores) { this.scores = new EnumMap<>(scores); }  // 새로운 Map을 받아와 기존 EnumMap에 반영
    public void updateScores(Result result) {
        scoreList.add(result);
        for (Map.Entry<ScoreType, Integer> entry : result.getScores().entrySet()) {
            scores.put(entry.getKey(), scores.getOrDefault(entry.getKey(), 0) + entry.getValue());
        }
    }
    public void setScoreList(List<Result> scoreList) { this.scoreList = scoreList; }

    @Override
    public String toString() {
        return "User{nickname='" + nickname + "'}";
    }
}

