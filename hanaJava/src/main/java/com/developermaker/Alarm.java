package com.developermaker;

import java.util.*;

public class Alarm {
    private final String[] texts = {
            "띠디디디-... 띠디디디-....",
            "현재 시각 07:00 AM, 일어날 시간이에요",
            "알람 시계는 총 4개, 랜덤으로 배치되어 있어요",
            "진짜 알람 시계를 찾아 알람을 꺼주세요!",
    };
    private final String[] choices = { "🕰",  "⏰", "⌚", "🕖" };
    private final String[] results = {
            "진짜 시계를 찾았다!",      // 성공
            "흠.. 이건 아닌 것 같아",   // 실패
    };

    public String getText(int index) {
        return texts[index];
    }
    public String[] getChoices() { return this.choices; }
    public String getResult(int index) {
        return results[index];
    }

    public void run(Scanner sc) throws InterruptedException {

        System.out.println("\n" + "═".repeat(60));
        System.out.println("☀️ 다음 날...");
        System.out.println("═".repeat(60) + "\n");
        Thread.sleep(1000);

        // 스토리 출력
        for (int i = 0; i < texts.length; i++) {
            System.out.println("📜 " + getText(i));
            Thread.sleep(1000);
        }

        // 랜덤으로 정답 선택
        Random rand = new Random();
        String correctAnswer = getChoices()[rand.nextInt(choices.length)];

        // 배열을 리스트로 변환 후 섞기
        List<String> choicesList = Arrays.asList(getChoices());
        Collections.shuffle(choicesList);

        // 섞인 리스트에서 정답의 위치 찾기
        int correctIndex = choicesList.indexOf(correctAnswer) + 1;

        System.out.println("\n" + "═".repeat(60));
        System.out.println("🎯 당신의 선택은?");
        System.out.println("═".repeat(60) + "\n");

        for (int i = 0; i < choices.length; i++) {
            System.out.println("🔹 " + (i + 1) + ". " + choicesList.get(i));
        }

        // 선택 입력 받기
        int choice;
        while (true) {
            System.out.print("\n🎤 선택 > ");
            try {
                choice = sc.nextInt(); // 정수 입력 받기

                if (choice >= 1 && choice <= 4) {
                    if (choice == correctIndex) {
                        System.out.println("✅ " + getResult(0));
                        break;
                    }
                    else
                        System.out.println("❌ " + getResult(1));
                } else {
                    System.out.println("⚠️ 잘못된 입력입니다. 1~4 사이의 숫자를 입력하세요.");
                }
            } catch (Exception e) {
                System.out.println("⚠️ 숫자를 입력하세요! (1~4)");
                sc.nextLine(); // 버퍼 비우기 (잘못된 입력 제거)
            }
        }

        Thread.sleep(1500);
    }
}
