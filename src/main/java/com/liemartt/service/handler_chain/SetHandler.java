package com.liemartt.service.handler_chain;

import com.liemartt.model.MatchScore;
import com.liemartt.model.PlayerScore;

public class SetHandler extends BaseHandler {
    @Override
    public void handle(MatchScore match) {
        PlayerScore pointWinner = match.getPointWinner();
        PlayerScore opponent = match.getOpponent();
        pointWinner.addSet();
        pointWinner.resetGameCounter();
        opponent.resetGameCounter();
        if (pointWinner.getSetCounter() == 2) {
            match.setWinner(pointWinner);
        }
    }
}
