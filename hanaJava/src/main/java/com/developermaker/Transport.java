package com.developermaker;

import com.developermaker.entity.Result;
import com.developermaker.entity.ScoreType;

import java.util.Map;

public class Transport extends BaseScenario {
    private final String[] texts = {
            "면접장까지 1시간 반... 이번에 새로 생긴 버스가 빠르다고 한다.",
            "새롭게 도전해볼까?",
    };
    private final String[] choices = {
            "검증되지 않은 노선은 좀.. 안전하게 지하철 타고 가기",
            "30분이나 빠르다고?! 새로 생긴 버스타고가기",
    };
    private final Result[] results = {
            new Result("transportResult0", "안내 방송: 아- 아.... 현재 차량 고장으로 인해.. 열차가 지연 운행 되고 있습니다...\n" + "뭐라고?! 큰일 났다... 빨리 버스를 타러 가야해 !!", Map.of(
                    ScoreType.WITH_CUSTOMER, 5
            )),
            new Result("transportResult1", "새로 생겼다더니 좌석도 너무 편안하고 빨라서 좋다 ~~", Map.of(
                    ScoreType.OPENNESS, 5
            )),
    };

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
}
