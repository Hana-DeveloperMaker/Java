package com.developermaker.utils;

import com.developermaker.entity.Result;
import com.developermaker.entity.User;
import com.developermaker.entity.ScoreType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.EnumMap;

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

        Map<String, User> users = mapper.readValue(file, new TypeReference<>() {});

        // JSON에서 로드할 때 scores를 EnumMap으로 변환
        for (User user : users.values()) {
            Map<ScoreType, Integer> fixedScores = new EnumMap<>(ScoreType.class);
            for (ScoreType type : ScoreType.values()) {
                fixedScores.put(type, user.getScores().getOrDefault(type, 0));
            }
            user.setScores(fixedScores);
        }

        return users;
    }

    // 닉네임 저장 (중복 시 false)
    public static void saveUser(User user) throws Exception {
        Map<String, User> users = loadUsers();
        users.put(user.getNickname(), user);
        mapper.writeValue(new File(FILE_PATH), users);
    }

    // 닉네임 중복 여부 확인
    public static boolean isNicknameTaken(String nickname) throws Exception {
        Map<String, User> users = loadUsers();
        return users.containsKey(nickname);
    }

    // 특정 유저의 점수 추가 후 업데이트
    public static boolean setUserScore(String nickname, Result result) throws Exception {
        Map<String, User> users = loadUsers();

        User user = users.get(nickname);
        if (user == null) {
            return false; // 사용자 없음
        }

        user.updateScores(result);

        // 파일에 저장
        mapper.writeValue(new File(FILE_PATH), users);
        return true;
    }
}