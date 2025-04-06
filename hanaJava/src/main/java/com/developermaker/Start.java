package com.developermaker;

import com.developermaker.entity.User;
import com.developermaker.utils.JsonUtil;

import java.util.Scanner;

public class Start {
    public User play(Scanner sc) throws Exception {

        System.out.println("\n" + "═".repeat(60));
        System.out.println("           🚀 개발자 키우기에 오신 여러분 환영합니다! 🚀");
        System.out.println("           ✨ 하나금융TI의 멋진 개발자를 꿈꾸는 당신! ✨");
        System.out.println("    🎯 면접 전날부터 당일까지, 최적의 결정을 내려 합격자가 되세요! 🎯");
        System.out.println("═".repeat(60) + "\n");

        System.out.print("📝 사용할 닉네임을 입력해주세요: ");
        String name = sc.nextLine();

        while (JsonUtil.isNicknameTaken(name)) {
            System.out.println("\n⚠️ 이미 사용 중인 닉네임입니다. 선택해주세요:");
            System.out.println("🔹 1. 이전 기록 보기");
            System.out.println("🔹 2. 이전 기록 삭제하고 새로 시작");
            System.out.println("🔹 3. 다른 닉네임 사용하기");
            System.out.println("🔹 4. 종료");
            System.out.print("🎤 선택 > ");

            String input = sc.nextLine();

            switch (input) {
                case "1" -> {
                    User existingUser = JsonUtil.loadUserByNickname(name);
                    if (existingUser != null) {
                        Carousel carousel = new Carousel();
                        carousel.play(existingUser, false);
                    } else {
                        System.out.println("\n⚠️ 해당 유저 정보를 찾을 수 없습니다.");
                    }
                }
                case "2" -> {
                    boolean deleted = JsonUtil.deleteUserByNickname(name);
                    if (deleted)
                        System.out.println("\n🗑 기록이 삭제되었습니다. 같은 닉네임으로 새 게임을 시작합니다.");
                    else
                        System.out.println("\n⚠️ 삭제에 실패했습니다. 다시 시도해주세요.");
                }
                case "3" -> {
                    System.out.print("\n📝 사용할 닉네임을 다시 입력해주세요: ");
                    name = sc.nextLine();
                }
                case "4" -> {
                    System.exit(0);
                }
                default -> System.out.println("\n⚠️ 잘못된 입력입니다. 1~4 중 하나를 선택해주세요.");
            }
        }

        User user = new User(name);

        try {
            JsonUtil.saveUser(user);
            System.out.println("✅ 닉네임 등록 완료! 환영합니다, " + name + "님! 🎉");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
