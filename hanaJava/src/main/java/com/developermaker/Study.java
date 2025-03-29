package com.developermaker;

class Study extends BaseScenario {
    private final String[] texts = {
            "내일 드디어 하나금융TI 최종 면접이다! 면접 준비를 더 할까, 쉴까?",
            "(따르르릉)",
            "누구지? 이 시간에 전화 올 사람이 없는데... (딸각)",
            "???: 나 고민이 있는데 혹시 들어줄 수 있어...?",
            "면접까지 12시간 남은 시각가장 친한 친구가 고민을 들어달라 한다... 어떻게 할까?"
    };
    private final String[] choices = {
            "그래도 내일이 면접인데.. 면접 준비를 더 하다 잔다",
            "친구가 최고지! 친구 고민상담 들어준다",
            "아 몰라... 그냥 쉰다"
    };
    private final String[] results = {
            "좀 정리가 되는 것 같아",
            "친구: 아니 글쎄 여자친구가 .... ....",
            "잠도 안오는데 유튜브나 봐야겠다 ~"
    };

    @Override protected String getScene() { return "📖 당신의 이야기 시작됩니다..."; }
    @Override protected String[] getTexts() { return texts; }
    @Override protected String[] getChoices() { return choices; }
    @Override protected String[] getResults() { return results; }
    @Override protected boolean isRandomChoice() { return false; }
}