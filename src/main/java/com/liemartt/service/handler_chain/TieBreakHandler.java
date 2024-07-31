package com.liemartt.service.handler_chain;

import com.liemartt.model.MatchScore;
import com.liemartt.model.PlayerScore;

public class TieBreakHandler extends BaseHandler {
    @Override
    public void handle(MatchScore match) {
        PlayerScore pointWinner = match.getPointWinner();
        PlayerScore opponent = match.getOpponent();
        if (match.isTieBreak()) {
            match.getPointWinner().addTieBreak();
            if (pointWinner.getTieBreaksCounter() == 7) {
                match.setTieBreak(false);
                pointWinner.resetTieBreaksCounter();
                opponent.resetTieBreaksCounter();
                nextHandler.handle(match);
            }

        } else {
            nextHandler.handle(match);
        }
    }
}
