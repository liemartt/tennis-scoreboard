package com.liemartt.service.handler_chain;

import com.liemartt.model.MatchScore;
import com.liemartt.model.PlayerScore;

public class PointHandler implements ScoreCalculationHandler {
    private ScoreCalculationHandler nextHandler;

    @Override
    public void setNext(ScoreCalculationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle(MatchScore match) {
        PlayerScore pointWinner = match.getPointWinner();
        PlayerScore opponent = match.getOpponent();
        if (match.isTieBreak()) {
            nextHandler.handle(match);
        } else if (pointWinner.getPointCounter() <= 30) {
            pointWinner.addPoint();
        } else if (pointWinner.getPointCounter() == 40 && opponent.getPointCounter() <= 30) {
            nextHandler.handle(match);
        } else if (pointWinner.getPointCounter() == 40 && pointWinner.isAdvantage()) {
            pointWinner.setAdvantage(false);
            nextHandler.handle(match);
        } else {
            if (opponent.isAdvantage()) {
                opponent.setAdvantage(false);
            } else {
                pointWinner.setAdvantage(true);
            }
        }
    }
}
