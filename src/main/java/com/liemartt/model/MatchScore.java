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

    public Long getPlayer1Id() {
        return firstPlayerScore.getId();
    }

    public Long getPlayer2Id() {
        return secondPlayerScore.getId();
    }

    public PlayerScore getPointWinner(Long winnerId) {
        if (firstPlayerScore.getPlayer().getId().equals(winnerId)) {
            return firstPlayerScore;
        } else return secondPlayerScore;
    }

    public PlayerScore getOpponent(Long winnerId) {
        if (!firstPlayerScore.getPlayer().getId().equals(winnerId)) {
            return firstPlayerScore;
        } else return secondPlayerScore;
    }

}
