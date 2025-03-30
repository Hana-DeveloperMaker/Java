package com.developermaker;

import com.developermaker.entity.Result;
import com.developermaker.entity.ScoreType;
import com.developermaker.entity.User;
import com.developermaker.utils.JsonUtil;

import java.util.*;

public class Interview extends BaseScenario {
    private final String[] texts = {
            "건물이 너무 멋지잖아?! 꼭 합격하고 말거야!!!",
            "님 들어와주세요!",
            "면접관: 안녕하세요. 하나 금융 티아이 최종 면접 시작하겠습니다.",
            "면접 복장을 보니 상당히 프로페셔널한 인상을 주네요. 혹시 이전에도 여러 기업에서 개발자로 일하셨나요",
            "..... ..... ..... ..... ..... .....",
            "면접관: 네, 이상으로 면접 모두 마치겠습니다. 혹시 마지막으로 하시고 싶은 말씀 있으실까요?"
    };
    private final String[] choices = {
            "아까 미쳐 제대로 답변하지 못한 질문에 대한 보완을 하자!",
            "면접도 망한 것 같은데, 빨리 끝내고 싶어... 최종 인사로 마무리 하자.",
    };
    private final Result[] results = {
            new Result("transportResult0", "아까 제가 했던 답변을 추가 보완해도 괜찮을까요?\n" + "... ... ...", Map.of(
                    ScoreType.EXCELLENCE, 5,
                    ScoreType.PASSION, 5
            )),
            new Result("transportResult1", "뽑아주시면 열심히 하겠습니다!", Map.of(
                    ScoreType.EXCELLENCE, -5
            )),
    };

    @Override
    public void play(Scanner sc, User user) throws InterruptedException {
        print(getScene());
        Thread.sleep(1500);

        for (int i = 0; i < texts.length; i++) {
            String text = texts[i];
            if (i == 1) {
                System.out.println("📜 " + user.getNickname() + text);
            } else {
                System.out.println("📜 " + text);
            }
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
                            print("🔮 당신의 선택 결과는...");
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

        Thread.sleep(1500);
    }

    @Override
    protected String getScene() {
        return "😵 휴 드디어 도착했다.";
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
}
