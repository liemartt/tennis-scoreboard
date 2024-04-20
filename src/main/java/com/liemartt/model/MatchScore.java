package com.liemartt.model;

import lombok.Data;

@Data
public class MatchScore {
    private final Match match;
    private final PLayerScore player1;
    private final PLayerScore player2;
    private boolean tieBreak;

    public MatchScore(Match match) {
        this.match = match;
        this.player1 = new PLayerScore(match.getPlayer1());
        this.player2 = new PLayerScore(match.getPlayer2());
    }

}
