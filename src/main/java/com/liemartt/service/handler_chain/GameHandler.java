package com.liemartt.service.handler_chain;

import com.liemartt.model.MatchScore;
import com.liemartt.model.PlayerScore;

public class GameHandler extends BaseHandler {
    @Override
    public void handle(MatchScore match) {
        PlayerScore pointWinner = match.getPointWinner();
        PlayerScore opponent = match.getOpponent();
        pointWinner.resetPointCounter();
        opponent.resetPointCounter();
        if (match.isTieBreak()) {
            nextHandler.handle(match);
        } else if (pointWinner.getGameCounter() == 5 && opponent.getGameCounter() == 6) {
            pointWinner.addGame();
            match.setTieBreak(true);
        } else if ((pointWinner.getGameCounter() == 5 && opponent.getGameCounter() <= 4) ||
                (pointWinner.getGameCounter() == 6 && opponent.getGameCounter() == 5)) {
            pointWinner.addGame();
            nextHandler.handle(match);
        } else {
            pointWinner.addGame();
            pointWinner.resetPointCounter();
            opponent.resetPointCounter();
        }
    }
}
