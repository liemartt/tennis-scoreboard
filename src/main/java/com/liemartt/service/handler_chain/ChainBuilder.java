package com.liemartt.service.handler_chain;

import com.liemartt.model.MatchScore;

public class ChainBuilder extends BaseHandler {

    public static ScoreCalculationHandler buildChain() {
        ScoreCalculationHandler pointHandler = new PointHandler();
        ScoreCalculationHandler gameHandler = new GameHandler();
        ScoreCalculationHandler tieBreakHandler = new TieBreakHandler();
        ScoreCalculationHandler setHandler = new SetHandler();
        pointHandler.setNext(gameHandler);
        gameHandler.setNext(tieBreakHandler);
        tieBreakHandler.setNext(setHandler);
        return pointHandler;
    }

    @Override
    public void handle(MatchScore match) {

    }
}
