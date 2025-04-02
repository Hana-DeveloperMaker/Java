package com.developermaker.utils;

import java.io.IOException;

public class ScannerUtil {

    void clearPreviousInput() {
        try {
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (IOException e) {
            System.err.println("입력 버퍼를 비우는 중 오류 발생: " + e.getMessage());
        }
    }
//   // 확인하기 위한 main 구현부
//    public static void main(String[] args) throws InterruptedException, IOException {
//        Scanner sc = new Scanner(System.in);
//
//        // 많은 줄의 출력 시뮬레이션 (각 줄마다 잠시 딜레이 추가)
//        for (int i = 0; i < 50; i++) {
//            System.out.println("출력 메시지 " + (i + 1));
//            Thread.sleep(100);  // 0.1초 지연 (실제 상황에 맞게 조절)
//        }
//
//        // 출력이 모두 끝난 후, 혹시 남아 있을 수 있는 입1231233력값을 버리는 과정
//        while (System.in.available() > 0) {
//            System.in.read();
//        }
//
//        // 이제 사용자로부터 입력 받기
//        System.out.println("출력이 모두 완료되었습니다. 이제 입력을 해주세요:");
//
//        String userInput = sc.nextLine();
//        System.out.println("입력받은 값: " + userInput);
//    }
}
