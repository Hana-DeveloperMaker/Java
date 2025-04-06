package com.developermaker.utils;

import com.developermaker.entity.Result;
import com.developermaker.entity.ScoreType;
import com.developermaker.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private static final String FILE_PATH = "data/users.json";

    // users.json을 Map<String, User>로 로드
    private static Map<String, User> loadUsers() throws Exception {
        File file = new File(FILE_PATH);

        // 부모 디렉토리가 없으면 먼저 생성
        file.getParentFile().mkdirs();

        // 파일이 없으면 새로 만들고 빈 Map 저장
        if (!file.exists()) {
            mapper.writeValue(file, new HashMap<String, User>());
            return new HashMap<>();
        }

        Map<String, User> users = mapper.readValue(file, new TypeReference<>() {
        });

        // JSON에서 로드할 때 scores를 EnumMap으로 변환
        for (User user : users.values()) {
            Map<ScoreType, Integer> fixedScores = new EnumMap<>(ScoreType.class);
            for (ScoreType type : ScoreType.values()) {
                fixedScores.put(type, user.getScores().getOrDefault(type, 0));
            }
            user.setScores(fixedScores);

            // Result.scores도 EnumMap으로 변환
            for (Result result : user.getScoreList()) {
                Map<ScoreType, Integer> fixedResultScores = new EnumMap<>(ScoreType.class);
                for (Map.Entry<ScoreType, Integer> entry : result.getScores().entrySet()) {
                    fixedResultScores.put(entry.getKey(), entry.getValue());
                }
                result.setScores(fixedResultScores);
            }
        }


        return users;
    }

    // 닉네임 저장 (중복 시 false)
    public static void saveUser(User user) throws Exception {
        Map<String, User> users = loadUsers();
        User existing = users.get(user.getNickname());

        if (existing != null) {
            // 1. 점수 누적
            for (Map.Entry<ScoreType, Integer> entry : existing.getScores().entrySet()) {
                ScoreType type = entry.getKey();
                int oldScore = entry.getValue();
                int newScore = user.getScores().getOrDefault(type, 0);
                user.getScores().put(type, oldScore + newScore);
            }

            // 2. 기존 scoreList + 새로운 것만 뒤에 붙이기
            java.util.List<Result> merged = new java.util.ArrayList<>(existing.getScoreList());
            for (Result newResult : user.getScoreList()) {
                if (!merged.contains(newResult)) {
                    merged.add(newResult); // 순서 보존하며 뒤에 추가
                }
            }

            user.setScoreList(merged);
        }

        users.put(user.getNickname(), user);
        mapper.writeValue(new File(FILE_PATH), users);
    }

    // 닉네임 중복 여부 확인
    public static boolean isNicknameTaken(String nickname) throws Exception {
        Map<String, User> users = loadUsers();
        return users.containsKey(nickname);
    }

    // 특정 유저의 점수 추가 후 업데이트
    public static boolean setUserScore(User user, Result result) throws Exception {
        Map<String, User> users = loadUsers();

        User storedUser = users.get(user.getNickname());
        if (storedUser == null) {
            return false; // 사용자 없음
        }

        storedUser.updateScores(result);

        // 파일에 저장
        mapper.writeValue(new File(FILE_PATH), users);
        return true;
    }

    public static void updateDressCodeOnly(User user) throws Exception {
        Map<String, User> users = loadUsers();
        User storedUser = users.get(user.getNickname());

        if (storedUser != null) {
            storedUser.setDressCode(user.getDressCode()); // ✅ 드레스 코드만 반영
            mapper.writeValue(new File(FILE_PATH), users);
        }
    }

    public static void updateIsPassedOnly(User user) throws Exception {
        Map<String, User> users = loadUsers();
        User storedUser = users.get(user.getNickname());

        if (storedUser != null) {
            storedUser.setIsSuccessed(user.getIsSuccessed()); // ✅ 합불 여부만 반영
            mapper.writeValue(new File(FILE_PATH), users);
        }
    }

    public static User loadUserByNickname(String nickname) throws Exception {
        Map<String, User> users = loadUsers();
        return users.get(nickname);
    }

    public static boolean deleteUserByNickname(String nickname) throws Exception {
        Map<String, User> users = loadUsers();
        if (users.containsKey(nickname)) {
            users.remove(nickname);
            saveUsers(users); // 파일에 반영
            return true;
        }
        return false;
    }

    private static void saveUsers(Map<String, User> users) throws IOException {
        File file = new File(FILE_PATH);
        mapper.writeValue(file, users);
    }

}