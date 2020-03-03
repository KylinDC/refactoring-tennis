package cn.xpbootcamp.tennis.game1;

import cn.xpbootcamp.tennis.TennisGame;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class TennisGame1 implements TennisGame {

    private String firstPlayerName;
    private String secondPlayerName;
    private int firstPlayerScore;
    private int secondPlayerScore;

    private Map<Integer, String> scoreMap = ImmutableMap.<Integer, String>builder()
            .put(0, "Love")
            .put(1, "Fifteen")
            .put(2, "Thirty")
            .put(3, "Forty")
            .build();

    public TennisGame1(String firstPlayerName, String secondPlayerName) {
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
    }

    public void wonPoint(String playerName) {
        if (firstPlayerName.equals(playerName)) {
            firstPlayerScore++;
        } else {
            secondPlayerScore++;
        }
    }

    public String getScore() {
        if (isTwoScoreEquals()) {
            return getScoreWhenEqual();
        }

        if (isAnyScoreOverThree()) {
            return getScoreWhenOverThree();
        }

        String scoreResult = "";
        int tempScore = 0;
        for (int i = 1; i < 3; i++) {
            if (i == 1) tempScore = firstPlayerScore;
            else {
                scoreResult += "-";
                tempScore = secondPlayerScore;
            }
            scoreResult += scoreMap.get(tempScore);
        }

        return scoreResult;
    }

    private boolean isTwoScoreEquals() {
        return firstPlayerScore == secondPlayerScore;
    }

    private String getScoreWhenEqual() {
        if (firstPlayerScore <= 2) {
            return scoreMap.get(firstPlayerScore) + "-All";
        }
        return "Deuce";
    }

    private boolean isAnyScoreOverThree() {
        return firstPlayerScore >= 4 || secondPlayerScore >= 4;
    }

    private String getScoreWhenOverThree() {
        int scoreGap = firstPlayerScore - secondPlayerScore;
        String scorePrefix = Math.abs(scoreGap) == 1 ? "Advantage " : "Win for ";
        String scorePlayer = scoreGap > 0 ? firstPlayerName : secondPlayerName;
        return scorePrefix + scorePlayer;
    }
}
