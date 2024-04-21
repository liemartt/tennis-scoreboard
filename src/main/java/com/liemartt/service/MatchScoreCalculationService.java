package com.liemartt.service;

import com.liemartt.model.PLayerScore;
import com.liemartt.model.MatchScore;
import com.liemartt.model.Player;

public class MatchScoreCalculationService {
    private final MatchScore matchScore;
    private PLayerScore ally;
    private PLayerScore enemy;

    public MatchScoreCalculationService(MatchScore matchScore) {
        this.matchScore = matchScore;
    }

    public void addPointToPlayer(Player player) {
        if (player.equals(getPlayer1())) {
            ally = matchScore.getPlayer1();
            enemy = matchScore.getPlayer2();
        } else {
            enemy = matchScore.getPlayer1();
            ally = matchScore.getPlayer2();
        }
        if (matchScore.isTieBreak()) {
            addTieBreakPoint();
            return;
        }

        if (ally.getPointCounter() <= 2) ally.addPoint();
        else if (ally.getPointCounter() == 3 && enemy.getPointCounter() <= 2) {
            addGameToAlly();
        } else if (ally.getPointCounter() == 3 && ally.isAdvantage()) {
            ally.setAdvantage(false);
            addGameToAlly();
        } else {
            if (enemy.isAdvantage()) enemy.setAdvantage(false);
            else ally.setAdvantage(true);
        }
    }

    private void addGameToAlly() {
        ally.addGame();
        ally.resetPointCounter();
        enemy.resetPointCounter();

        if (ally.getGameCounter() == 6 && enemy.getGameCounter() == 6) {
            matchScore.setTieBreak(true);
        } else if (ally.getGameCounter() == 7 && enemy.getGameCounter() <= 5) {
            addSetToAlly();
        }
    }

    private void addTieBreakPoint() {
        ally.addTieBreak();
        if (ally.getTieBreaksCounter() == 7) {
            matchScore.setTieBreak(false);
            ally.resetTieBreaksCounter();
            enemy.resetTieBreaksCounter();
            addSetToAlly();
        }
    }

    private void addSetToAlly() {
        ally.addSet();
        ally.resetGameCounter();
        enemy.resetGameCounter();
        if (ally.getSetCounter() == 2) {
            matchScore.setFinished(true);
            matchScore.setWinner(ally);
        }
    }

    private Player getPlayer1() {
        return matchScore.getPlayer1().getPlayer();
    }

}
