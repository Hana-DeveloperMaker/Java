package com.developermaker;

import com.developermaker.entity.Result;
import com.developermaker.entity.ScoreType;
import com.developermaker.entity.User;
import com.developermaker.utils.JsonUtil;

import java.util.*;

public class Transport extends BaseScenario {
    private final String[] texts = {"면접장까지 1시간 반... 이번에 새로 생긴 버스가 빠르다고 한다.", "새롭게 도전해볼까?",};
    private final String[] choices = {"검증되지 않은 노선은 좀.. 안전하게 지하철 타고 가기", "30분이나 빠르다고?! 새로 생긴 버스타고가기",};
    private final Result[] results = {new Result("transportResult0", "안내 방송: 아- 아.... 현재 차량 고장으로 인해.. 열차가 지연 운행 되고 있습니다...\n" + "뭐라고?! 큰일 났다... 빨리 버스를 타러 가야해 !!", Map.of(ScoreType.WITH_CUSTOMER, 5)), new Result("transportResult1", "새로 생겼다더니 좌석도 너무 편안하고 빨라서 좋다 ~~", Map.of(ScoreType.OPENNESS, 5)),
            new Result("transportResult2", "???: 예끼 이놈아! 공공장소에 누가 그런 차림으로 다녀?! 당장 나가!!", Map.of(ScoreType.WITH_CUSTOMER, -100)) {
            }};

    @Override
    protected String getScene() {
        return "... ... ... ... ...";
    }

    @Override
    protected String[] getTexts() {
        return texts;
    }

    @Override
    protected String[] getChoices() {
        return choices;
    }

    @Override
    protected Result[] getResults() {
        return results;
    }

    @Override
    protected boolean isRandomChoice() {
        return false;
    }

    public boolean play(Scanner sc, User user, boolean isEasterEgg) throws InterruptedException {
        isEasterEgg = user.getDressCode() == 0;
        print(getScene());
        Thread.sleep(1500);

        // 스토리 출력
        for (String text : getTexts()) {
            System.out.println("📜 " + text);
            Thread.sleep(1000);
        }

        // 선택지 출력
        print("🎯 당신의 선택은?");

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
//                sc.nextLine(); // 입력 버퍼 비우기

                if (choice >= 1 && choice <= choicesList.size()) {
                    if (getResults().length > 0) {  // 결과가 있을 경우만 출력
                        if (isRandomChoice() && choice == correctIndex) {
                            System.out.println("✅ " + getResults()[0].getMessage());
                            break;
                        } else if (isRandomChoice()) {
                            System.out.println("❌ " + getResults()[1].getMessage());
                        } else {
                            Result result = null;
                            if (isEasterEgg) {
                                result = getResults()[2];
                            } else {
                                result = getResults()[choice - 1];
                            }
                            print("🔮 당신의 선택 결과는...");
//                            Thread.sleep(500);
                            System.out.println("✅ " + result.getMessage());
                            // 점수 업데이트 및 저장
                            try {
                                boolean updateSuccess = JsonUtil.setUserScore(user, result);
                                if (!updateSuccess)
                                    System.out.println("⚠️ 점수 업데이트에 실패했습니다. 닉네임을 확인해주세요.");
                            } catch (Exception e) {
                                System.out.println("⚠️ 점수 업데이트 중 오류가 발생했습니다: " + e.getMessage());
                            }
                            break;
                        }
                    } else
                        break;
                } else {
                    System.out.println("⚠️ 잘못된 입력입니다. 1~" + choicesList.size() + " 사이의 숫자를 입력하세요.");
                }
            } catch (Exception e) {
                System.out.println("⚠️ 숫자를 입력하세요! (1~" + choicesList.size() + ")");
                sc.nextLine();
            }
        }
        return user.getDressCode() != 0;
    }

}
