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

        String scoreResult = "";
        int tempScore = 0;

        if (firstPlayerScore == secondPlayerScore) {
            if (firstPlayerScore <= 2) {
                return scoreMap.get(firstPlayerScore) + "-All";
            }
            return "Deuce";
        }

        if (firstPlayerScore >= 4 || secondPlayerScore >= 4) {
            int minusResult = firstPlayerScore - secondPlayerScore;
            if (minusResult == 1) scoreResult = "Advantage player1";
            else if (minusResult == -1) scoreResult = "Advantage player2";
            else if (minusResult >= 2) scoreResult = "Win for player1";
            else scoreResult = "Win for player2";
            return scoreResult;
        }

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
}
