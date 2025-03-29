package com.developermaker;

public class Dress extends BaseScenario {
    private final String[] texts = {
            "음 면접에는 어떤 옷을 입고 가야 될까?",
    };
    private final String[] choices = {
            "옷장을 연다.",
    };
    private final String[] results = {};

    @Override protected String getScene() { return "👔 외출 준비 중..."; }
    @Override protected String[] getTexts() { return texts; }
    @Override protected String[] getChoices() { return choices; }
    @Override protected String[] getResults() { return results; }
    @Override protected boolean isRandomChoice() { return false; }
}
