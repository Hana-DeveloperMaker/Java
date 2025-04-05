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
            "..... ..... ..... ..... ..... .....",
            "면접관: 네, 이상으로 면접 모두 마치겠습니다. 혹시 마지막으로 하시고 싶은 말씀 있으실까요?"
    };
    private final String[] choices = {
            "아까 미처 제대로 답변하지 못한 질문에 대한 보완을 하자!",
            "면접도 망한 것 같은데, 빨리 끝내고 싶어... 최종 인사로 마무리 하자.",
    };
    private final Result[] results = {
            new Result("interview0", "아까 제가 했던 답변을 추가 보완해도 괜찮을까요?\n" + "... ... ...", Map.of(
                    ScoreType.EXCELLENCE, 5,
                    ScoreType.PASSION, 5
            )),
            new Result("interview0", "뽑아주시면 열심히 하겠습니다!", Map.of(
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
            } else if (i == 2) {
                String message = "📜 면접관: " + switch (user.getDressCode()) {
                    case 1 -> "면접 복장을 보니 상당히 프로페셔널한 인상을 주네요. 혹시 이전에도 여러 기업에서 개발자로 일하셨나요?";
                    case 2 -> "실내에서는 편한 신발을 선호하시는 것 같은데, 사무실에서도 이런 복장을 유지하실 생각이신가요?";
                    case 3 -> "상의와 하의의 조합이 독특하네요. 혹시 특정한 이유가 있나요?";
                    case 4 -> "보통 개발자들이 편한 복장을 선호하긴 하지만, 여기까지 자유롭게 오신 건 처음 보네요. 혹시 이런 스타일이 업무 효율에 영향을 주나요?";
                    case 5 -> "스타트업에서 일해본 경험이 많을 것 같은데, 맞나요?";
                    case 6 -> "굉장히 편한 복장이네요. 회사에서도 자유로운 분위기를 선호하시는 편인가요?";
                    case 7 -> "복장의 조합이 개발자다운 스타일인데, 본인의 개발 철학을 한 마디로 표현한다면?";
                    case 8 -> "혹시 해커톤 같은 행사에 자주 참여하시나요? 이 복장이 해커톤 참가자들에게서 많이 보이더라고요.";
                    case 9 -> "굉장히 신뢰감 있는 인상이네요. 혹시 팀을 리딩해 본 경험이 있나요?";
                    case 10 -> "회사에서는 어느 정도의 드레스 코드를 기대하는데, 본인의 스타일을 유지하면서도 이를 조화롭게 맞출 수 있을까요?";
                    case 11 -> "복장이 특이하네요. 혹시 본인만의 독특한 개발 방식이 있나요?";
                    case 12 -> "정말 자유로운 스타일이시네요. 개발하면서 가장 중요하게 생각하는 요소는 무엇인가요?";
                    case 13 -> "혹시 CTO 경험이 있으신가요? 리더십 있는 개발자로 보입니다.";
                    case 14 -> "이 복장은 거의 창업자의 느낌인데, 혹시 창업 경험이 있나요?";
                    case 15 -> "신발이 눈에 띄네요. 혹시 어제 사신 건가요?";
                    case 16 -> "이 복장으로 면접 오신 건 처음 봅니다. 이 스타일이 본인에게 주는 장점이 있나요?";
                    default -> "유효하지 않은 dresscode 입니다.";
                };
                System.out.println("📜 " + text);
                Thread.sleep(1500);
                System.out.println(message);
            } else {
                System.out.println("📜 " + text);
            }
            Thread.sleep(1500);
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
