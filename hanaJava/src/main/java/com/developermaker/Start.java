package com.developermaker;

import java.util.Scanner;

public class Start {
    private final String[] texts = {
            "내일 드디어 하나금융TI 최종 면접이다! 면접 준비를 더 할까, 쉴까?",
            "(따르르릉)",
            "누구지? 이 시간에 전화 올 사람이 없는데... (딸각)",
            "???: 나 고민이 있는데 혹시 들어줄 수 있어...?",
            "면접까지 12시간 남은 시각가장 친한 친구가 고민을 들어달라 한다... 어떻게 할까?"
    };
    private final String[] chices = {
            "1. 그래도 내일이 면접인데.. 면접 준비를 더 하다 잔다",
            "2. 친구가 최고지! 친구 고민상담 들어준다",
            "3. 아 몰라... 그냥 쉰다"
    };
    private final String[] results = {
            "좀 정리가 되는 것 같아",
            "친구: 아니 글쎄 여자친구가 .... ....",
            "잠도 안오는데 유튜브나 봐야겠다 ~"
    };

    public String getText(int index) {
        return texts[index];
    }
    public String getChoice(int index) {
        return chices[index];
    }
    public String getResult(int index) {
        return results[index];
    }

    public void run() throws InterruptedException {
        Start start = new Start();
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < start.texts.length; i++) {
            System.out.println(start.getText(i));
            Thread.sleep(1000);
        }
        System.out.println("\n-------------------- Choice ---------------------\n");
        for (int i = 0; i < start.chices.length; i++) {
            System.out.println(start.getChoice(i));
        }
        int choice;
        while (true) {
            System.out.print("\n선택 > ");
            try {
                choice = sc.nextInt(); // 정수 입력 받기

                if (choice >= 1 && choice <= 3) {
                    break; // 유효한 입력이면 반복문 탈출
                } else {
                    System.out.println("잘못된 입력입니다. 1~3 사이의 숫자를 입력하세요.");
                }
            } catch (Exception e) {
                System.out.println("잘못된 입력입니다. 1~3 사이의 숫자를 입력하세요.");
                sc.nextLine(); // 버퍼 비우기 (잘못된 입력 제거)
            }
        }

        System.out.println("\n-------------------- Result ---------------------\n");
        switch (choice) {
            case 1:
                System.out.println(start.getResult(0));
                break ;
            case 2:
                System.out.println(start.getResult(1));
                break ;
            case 3:
                System.out.println(start.getResult(2));
                break ;
        }
        sc.close();
    }
}
