package com.developermaker;

class WakeUp extends BaseScenario {
    private final String[] texts = {
            "하아아아아암...",
            "7시에 일어났더니 피곤하네. 면접까지는 좀 시간이 남았는데 10분만 더 잘까?",
    };
    private final String[] choices = {
            "잠깐은 괜찮겠지... 다시 눕는다.",
            "일찍 일어난 김에 빨리 씻자! 정신을 부여잡고 씻으러 간다.",
    };
    private final String[] results = {
            "으악! 10분만 잔다는 게 그만... 늦었다! 얼른 일어나서 씻어야지!!",
            "부지런히 준비하니 여유롭고 좋아~ ♩♪♬",
    };

    @Override protected String getScene() { return "🛏️ 기상 후..."; }
    @Override protected String[] getTexts() { return texts; }
    @Override protected String[] getChoices() { return choices; }
    @Override protected String[] getResults() { return results; }
    @Override protected boolean isRandomChoice() { return false; }
}
