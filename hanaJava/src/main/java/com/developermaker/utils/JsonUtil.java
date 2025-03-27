package com.developermaker.utils;

import com.developermaker.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
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

        return mapper.readValue(file, new TypeReference<>() {});
    }


    // 닉네임 저장 (중복 시 false)
    public static boolean saveUser(User user) throws Exception {
        Map<String, User> users = loadUsers();
        if (users.containsKey(user.getNickname())) {
            return false; // 닉네임 중복
        }
        users.put(user.getNickname(), user);
        mapper.writeValue(new File(FILE_PATH), users);
        return true;
    }

    // 닉네임 중복 여부 확인
    public static boolean isNicknameTaken(String nickname) throws Exception {
        Map<String, User> users = loadUsers();
        return users.containsKey(nickname);
    }
}