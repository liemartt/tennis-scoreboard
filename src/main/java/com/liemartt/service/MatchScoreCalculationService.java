package com.liemartt.service;

import com.liemartt.model.MatchScore;
import com.liemartt.model.PlayerScore;

public class MatchScoreCalculationService {

    public void addPointToPlayer(MatchScore match, long winnerId) {
        PlayerScore pointWinner = match.getPointWinner(winnerId);
        PlayerScore opponent = match.getOpponent(winnerId);

        if (match.isTieBreak()) {
            addTieBreakPoint(match, pointWinner, opponent);
            return;
        }

        if (pointWinner.getPointCounter() <= 30) pointWinner.addPoint();
        else if (pointWinner.getPointCounter() == 40 && opponent.getPointCounter() <= 30) {
            addGameToPlayer(match, pointWinner, opponent);
        } else if (pointWinner.getPointCounter() == 40 && pointWinner.isAdvantage()) {
            pointWinner.setAdvantage(false);
            addGameToPlayer(match, pointWinner, opponent);
        } else {
            if (opponent.isAdvantage()) opponent.setAdvantage(false);
            else pointWinner.setAdvantage(true);
        }
    }

    private void addGameToPlayer(MatchScore match, PlayerScore pointWinner, PlayerScore opponent) {
        pointWinner.addGame();
        pointWinner.resetPointCounter();
        opponent.resetPointCounter();

        if (pointWinner.getGameCounter() == 6 && opponent.getGameCounter() == 6) {
            match.setTieBreak(true);
        } else if ((pointWinner.getGameCounter() == 6 && opponent.getGameCounter() <= 4) || (pointWinner.getGameCounter() == 7 && opponent.getGameCounter() == 5)) {
            addSetToPlayer(match, pointWinner, opponent);
        }
    }

    private void addTieBreakPoint(MatchScore match, PlayerScore pointWinner, PlayerScore opponent) {
        pointWinner.addTieBreak();
        if (pointWinner.getTieBreaksCounter() == 7) {
            match.setTieBreak(false);
            pointWinner.resetTieBreaksCounter();
            opponent.resetTieBreaksCounter();
            addSetToPlayer(match, pointWinner, opponent);
        }
    }

    private void addSetToPlayer(MatchScore matchScore, PlayerScore pointWinner, PlayerScore opponent) {
        pointWinner.addSet();
        pointWinner.resetGameCounter();
        opponent.resetGameCounter();
        if (pointWinner.getSetCounter() == 2) {
            matchScore.setFinished(true);
            matchScore.setWinner(pointWinner);
            matchScore.getMatch().setWinner(pointWinner.getPlayer());
        }
    }

}
