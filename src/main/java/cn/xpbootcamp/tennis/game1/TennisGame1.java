package cn.xpbootcamp.tennis.game1;

import cn.xpbootcamp.tennis.TennisGame;

import java.util.HashMap;
import java.util.Map;

public class TennisGame1 implements TennisGame {

    public static final int DEFAULT_SCORE = 0;

    private String firstPlayerName;
    private String secondPlayerName;
    private Map<String, Integer> scoreTable = new HashMap<>();

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

        String score = "";
        int tempScore = 0;

        if (firstPlayerScore == secondPlayerScore) {
            switch (firstPlayerScore) {
                case 0:
                    score = "Love-All";
                    break;
                case 1:
                    score = "Fifteen-All";
                    break;
                case 2:
                    score = "Thirty-All";
                    break;
                default:
                    score = "Deuce";
                    break;
            }
            return score;
        }

        if (firstPlayerScore >= 4 || secondPlayerScore >= 4) {
            int minusResult = firstPlayerScore - secondPlayerScore;
            if (minusResult == 1) score = "Advantage player1";
            else if (minusResult == -1) score = "Advantage player2";
            else if (minusResult >= 2) score = "Win for player1";
            else score = "Win for player2";
            return score;
        }

        for (int i = 1; i < 3; i++) {
            if (i == 1) tempScore = firstPlayerScore;
            else {
                score += "-";
                tempScore = secondPlayerScore;
            }
            switch (tempScore) {
                case 0:
                    score += "Love";
                    break;
                case 1:
                    score += "Fifteen";
                    break;
                case 2:
                    score += "Thirty";
                    break;
                case 3:
                    score += "Forty";
                    break;
            }
        }

        return score;
    }
}
