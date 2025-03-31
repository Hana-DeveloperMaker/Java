package com.developermaker;

import com.developermaker.entity.Result;
import com.developermaker.entity.ScoreType;

import java.util.Map;

public class Grandma extends BaseScenario {
    private final String[] texts = {
            "어제 잠을 많이 못자서 피곤하긴 한데...",
            "자리를 양보해 드릴까?",
    };
    private final String[] choices = {
            "마음이 안 좋아.. 자리를 양보한다.",
            "좋은 컨디션으로 면접 봐야지.. 모르는 척 한다.",
    };
    private final Result[] results = {
            new Result("grandmaResult0", "큰 짐을 든 할머니: 아이고 정말 고마워요...! 복 받을 거야!!", Map.of(
                    ScoreType.WITH_CUSTOMER, 10,
                    ScoreType.RESPECT, 10
            )),
            new Result("grandmaResult1", "Zzzzz....", Map.of(
                    ScoreType.WITH_CUSTOMER, -10,
                    ScoreType.RESPECT, -5
            )),
    };

    @Override
    protected String getScene() {
        return "👵 버스를 타고 가는데, 할머니 한 분이 큰 짐을 들고 타셨다....";
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
