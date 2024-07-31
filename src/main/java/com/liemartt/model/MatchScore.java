package com.liemartt.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class MatchScore {
    private final PlayerScore player1Score;
    private final PlayerScore player2Score;
    private PlayerScore lastPointWinner;
    private PlayerScore winner;
    private boolean tieBreak;
    private UUID uuid;

    public MatchScore(Player player1, Player player2) {
        this.player1Score = new PlayerScore(player1);
        this.player2Score = new PlayerScore(player2);
        this.uuid = UUID.randomUUID();
    }

    public PlayerScore getPointWinner() {
        if (player1Score.equals(lastPointWinner)) {
            return player1Score;
        } else return player2Score;
    }

    public void setPointWinner(Player winner) {
        if (player1Score.getPlayer().equals(winner)) {
            lastPointWinner = player1Score;
        } else if (player2Score.getPlayer().equals(winner)) {
            lastPointWinner = player2Score;
        } else {
            throw new RuntimeException("No such player in this match");
        }
    }

    public PlayerScore getOpponent() {
        if (!player1Score.equals(lastPointWinner)) {
            return player1Score;
        } else return player2Score;
    }

    public Player getPlayer1() {
        return player1Score.getPlayer();
    }

    public Player getPlayer2() {
        return player2Score.getPlayer();
    }
}
