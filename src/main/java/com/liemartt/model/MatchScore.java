package com.liemartt.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class MatchScore {
    private final Match match;
    private final PlayerScore firstPlayerScore;
    private final PlayerScore secondPlayerScore;
    private PlayerScore winner;
    private boolean isFinished;
    private boolean tieBreak;
    private UUID uuid;

    public MatchScore(Match match) {
        this.match = match;
        this.firstPlayerScore = new PlayerScore(match.getPlayer1());
        this.secondPlayerScore = new PlayerScore(match.getPlayer2());
        this.uuid = UUID.randomUUID();
    }
    public String getFormattedPoints(int score) {
        return switch (score) {
            case 0 -> "0";
            case 1 -> "15";
            case 2 -> "30";
            case 3 -> "40";
            default -> "Adv";
        };
    }
}
