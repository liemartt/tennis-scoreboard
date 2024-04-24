package com.liemartt.model;

import lombok.Data;

import java.util.UUID;

@Data
public class MatchScore {
    private final Match match;
    private final PLayerScore player1;
    private final PLayerScore player2;
    private PLayerScore winner;
    private boolean isFinished;
    private boolean tieBreak;
    private UUID uuid;

    public MatchScore(Match match) {
        this.match = match;
        this.player1 = new PLayerScore(match.getPlayer1());
        this.player2 = new PLayerScore(match.getPlayer2());
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
