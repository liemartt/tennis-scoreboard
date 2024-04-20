package com.liemartt.model;

import lombok.Data;
import lombok.Setter;

@Data
public class MatchScore {
    private final Match match;
    private final PLayerScore player1;
    private final PLayerScore player2;
    private PLayerScore winner;
    private boolean isFinished;
    private boolean tieBreak;


    public MatchScore(Match match) {
        this.match = match;
        this.player1 = new PLayerScore(match.getPlayer1());
        this.player2 = new PLayerScore(match.getPlayer2());
    }

}
