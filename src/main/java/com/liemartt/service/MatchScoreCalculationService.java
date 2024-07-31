package com.liemartt.service;

import com.liemartt.model.MatchScore;
import com.liemartt.service.handler_chain.ScoreCalculationHandler;

public class MatchScoreCalculationService {
    private final ScoreCalculationHandler scoreCalculationHandler;

    public MatchScoreCalculationService(ScoreCalculationHandler scoreCalculationHandler) {
        this.scoreCalculationHandler = scoreCalculationHandler;
    }

    public void execute(MatchScore match) {
        scoreCalculationHandler.handle(match);
    }
}
