package com.developermaker;

import com.developermaker.entity.Result;
import com.developermaker.entity.User;
import com.developermaker.utils.JsonUtil;

import java.util.*;

abstract class BaseScenario {
    protected abstract String getScene();
    protected abstract String[] getTexts();
    protected abstract String[] getChoices();
    protected abstract Result[] getResults();
    protected abstract boolean isRandomChoice();

    public void play(Scanner sc, User user) throws InterruptedException {
        System.out.println("\n" + "═".repeat(60));
        System.out.println(getScene());
        System.out.println("═".repeat(60) + "\n");
        Thread.sleep(1500);

        // 스토리 출력
        for (String text : getTexts()) {
            System.out.println("📜 " + text);
            Thread.sleep(1000);
        }

        // 선택지 출력
        System.out.println("\n" + "═".repeat(60));
        System.out.println("🎯 당신의 선택은?");
        System.out.println("═".repeat(60) + "\n");

        List<String> choicesList = new ArrayList<>(Arrays.asList(getChoices()));

        // 랜덤 선택지가 필요한 경우 섞기
        if (isRandomChoice()) {
            Collections.shuffle(choicesList);
        }

        for (int i = 0; i < choicesList.size(); i++) {
            System.out.println("🔹 " + (i + 1) + ". " + choicesList.get(i));
        }

        // 정답 설정 (랜덤인지 여부에 따라)
        int correctIndex = isRandomChoice() ? new Random().nextInt(choicesList.size()) + 1 : -1;

        // 사용자 입력 받기
        int choice;
        while (true) {
            System.out.print("\n🎤 선택 > ");
            try {
                choice = sc.nextInt();
                sc.nextLine(); // 입력 버퍼 비우기

                if (choice >= 1 && choice <= choicesList.size()) {
                    if (getResults().length > 0) {  // 결과가 있을 경우만 출력
                        if (isRandomChoice() && choice == correctIndex) {
                            System.out.println("✅ " + getResults()[0].getMessage());
                            break;
                        } else if (isRandomChoice()) {
                            System.out.println("❌ " + getResults()[1].getMessage());
                        } else {
                            Result result = getResults()[choice - 1];
                            System.out.println("\n" + "═".repeat(60));
                            System.out.println("🔮 당신의 선택 결과는...");
                            System.out.println("═".repeat(60) + "\n");
                            Thread.sleep(500);
                            System.out.println("✅ " + result.getMessage());
                            // 점수 업데이트 및 저장
                            try {
                                boolean updateSuccess = JsonUtil.setUserScore(user.getNickname(), result);
                                if (!updateSuccess)
                                    System.out.println("⚠️ 점수 업데이트에 실패했습니다. 닉네임을 확인해주세요.");
                            } catch (Exception e) {
                                System.out.println("⚠️ 점수 업데이트 중 오류가 발생했습니다: " + e.getMessage());
                            }
                            break;
                        }
                    }
                    else
                        break;
                } else {
                    System.out.println("⚠️ 잘못된 입력입니다. 1~" + choicesList.size() + " 사이의 숫자를 입력하세요.");
                }
            } catch (Exception e) {
                System.out.println("⚠️ 숫자를 입력하세요! (1~" + choicesList.size() + ")");
                sc.nextLine();
            }
        }

        Thread.sleep(1500);
    }
}