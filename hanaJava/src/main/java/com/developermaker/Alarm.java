package com.developermaker;

class Alarm extends BaseScenario {
    private final String[] texts = {
            "띠디디디-... 띠디디디-....",
            "현재 시각 07:00 AM, 일어날 시간이에요",
            "알람 시계는 총 4개, 랜덤으로 배치되어 있어요",
            "진짜 알람 시계를 찾아 알람을 꺼주세요!"
    };
    private final String[] choices = { "🕰", "⏰", "⌚", "🕖" };
    private final String[] results = {
            "진짜 시계를 찾았다!",  // 성공
            "흠.. 이건 아닌 것 같아" // 실패
    };

    @Override protected String getScene() { return "☀️ 다음 날..."; }
    @Override protected String[] getTexts() { return texts; }
    @Override protected String[] getChoices() { return choices; }
    @Override protected String[] getResults() { return results; }
    @Override protected boolean isRandomChoice() { return true; }
}