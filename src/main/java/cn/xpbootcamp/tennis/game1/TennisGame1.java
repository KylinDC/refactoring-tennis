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

        if (firstPlayerScore == secondPlayerScore) {
            return getScoreWhenEqual();
        }

        if (firstPlayerScore >= 4 || secondPlayerScore >= 4) {
            return getScoreWhenAnyOverThree();
        }

        return scoreMap.get(firstPlayerScore) + "-" + scoreMap.get(secondPlayerScore);
    }

    private String getScoreWhenEqual() {
        if (firstPlayerScore <= 2) {
            return scoreMap.get(firstPlayerScore) + "-All";
        }
        return "Deuce";
    }

    private String getScoreWhenAnyOverThree() {
        int scoreGap = firstPlayerScore - secondPlayerScore;
        String scorePrefix = Math.abs(scoreGap) == 1 ? "Advantage " : "Win for ";
        String scorePlayer = scoreGap > 0 ? firstPlayerName : secondPlayerName;
        return scorePrefix + scorePlayer;
    }
}
