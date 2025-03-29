package com.developermaker;

import com.developermaker.entity.User;
import com.developermaker.utils.JsonUtil;

import java.util.Scanner;

public class Start {
    public void play(Scanner sc) throws Exception {

        System.out.println("\n" + "═".repeat(60));
        System.out.println("           🚀 개발자 키우기에 오신 여러분 환영합니다! 🚀");
        System.out.println("           ✨ 하나금융TI의 멋진 개발자를 꿈꾸는 당신! ✨");
        System.out.println("    🎯 면접 전날부터 당일까지, 최적의 결정을 내려 합격자가 되세요! 🎯");
        System.out.println("═".repeat(60) + "\n");

        System.out.print("📝 사용할 닉네임을 입력해주세요: ");
        String name = sc.nextLine();

        while (JsonUtil.isNicknameTaken(name)) {
            System.out.println("⚠️ 이미 사용 중인 닉네임입니다. 다른 닉네임을 입력하세요.");
            System.out.print("📝 사용할 닉네임을 다시 입력해주세요: ");
            name = sc.nextLine();
        }

        User user = new User(name);

        try {
            JsonUtil.saveUser(user);
            System.out.println("✅ 닉네임 등록 완료! 환영합니다, " + name + "님! 🎉");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
