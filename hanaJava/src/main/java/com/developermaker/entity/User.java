package com.developermaker.entity;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class User {
    private String nickname;
    private Map<ScoreType, Integer> scores;
    private List<Result> scoreList;
    private int dressCode;

    public User() {
    }

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

    public String getNickname() {
        return nickname;
    }

    public Map<ScoreType, Integer> getScores() {
        return scores;
    }

    public List<Result> getScoreList() {
        return scoreList;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setScores(Map<ScoreType, Integer> scores) {
        this.scores = new EnumMap<>(scores);
    }  // 새로운 Map을 받아와 기존 EnumMap에 반영

    public void updateScores(Result result) {
        scoreList.add(result);
        for (Map.Entry<ScoreType, Integer> entry : result.getScores().entrySet()) {
            scores.put(entry.getKey(), scores.getOrDefault(entry.getKey(), 0) + entry.getValue());
        }
    }

    public void setScoreList(List<Result> scoreList) {
        this.scoreList = scoreList;
    }

    public int getDressCode() {
        return dressCode;
    }

    public void setDressCode(int dressCode) {
        this.dressCode = dressCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{").append("nickname='").append(nickname).append("',\n");

        sb.append("  scores={\n");
        for (Map.Entry<ScoreType, Integer> entry : scores.entrySet()) {
            sb.append("    ").append(entry.getKey()).append(": ").append(entry.getValue()).append(",\n");
        }
        sb.append("  },\n");

        sb.append("  scoreList=[\n");
        for (Result result : scoreList) {
            sb.append("    {\n");
            sb.append("      imgName: ").append(result.getImgName()).append(",\n");
            sb.append("      message: ").append(result.getMessage()).append(",\n");
            sb.append("      scores: ").append(result.getScores()).append("\n");
            sb.append("    },\n");
        }
        sb.append("  ]\n");

        sb.append("}");
        return sb.toString();
    }

}

