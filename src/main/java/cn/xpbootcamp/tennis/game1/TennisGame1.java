package cn.xpbootcamp.tennis.game1;

import cn.xpbootcamp.tennis.TennisGame;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public class TennisGame1 implements TennisGame {

    public static final int DEFAULT_SCORE = 0;

    private String firstPlayerName;
    private String secondPlayerName;
    private Map<String, Integer> scoreTable = new HashMap<>();
    private Map<Integer, String> scoreMap = ImmutableMap.<Integer, String>builder()
            .put(0, "Love")
            .put(1, "Fifteen")
            .put(2, "Thirty")
            .put(3, "Forty")
            .build();

    public TennisGame1(String firstPlayerName, String secondPlayerName) {
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
        scoreTable.put(firstPlayerName, DEFAULT_SCORE);
        scoreTable.put(secondPlayerName, DEFAULT_SCORE);
    }

    public void wonPoint(String playerName) {
        if (scoreTable.containsKey(playerName)) {
            scoreTable.put(playerName, scoreTable.get(playerName) + 1);
        }
    }

    public String getScore() {
        int firstPlayerScore = scoreTable.get(firstPlayerName);
        int secondPlayerScore = scoreTable.get(secondPlayerName);

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

    private String getScoreWhenOverThree() {
        int firstPlayerScore = scoreTable.get(firstPlayerName);
        int secondPlayerScore = scoreTable.get(secondPlayerName);

        int scoreGap = firstPlayerScore - secondPlayerScore;
        String scorePrefix = Math.abs(scoreGap) == 1 ? "Advantage " : "Win for ";
        String scorePlayer = scoreGap > 0 ? firstPlayerName : secondPlayerName;
        return scorePrefix + scorePlayer;
    }

    private boolean isAnyScoreOverThree() {
        int firstPlayerScore = scoreTable.get(firstPlayerName);
        int secondPlayerScore = scoreTable.get(secondPlayerName);
        return firstPlayerScore >= 4 || secondPlayerScore >= 4;
    }

    private boolean isTwoScoreEquals() {
        return scoreTable.get(firstPlayerName).equals(scoreTable.get(secondPlayerName));
    }

    private String getScoreWhenEqual() {
        int firstPlayerScore = scoreTable.get(firstPlayerName);
        if (firstPlayerScore <= 2) {
            return scoreMap.get(firstPlayerScore) + "-All";
        }
        return "Deuce";
    }
}
