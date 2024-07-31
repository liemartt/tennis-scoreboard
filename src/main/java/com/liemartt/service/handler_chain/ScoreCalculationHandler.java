package com.liemartt.service.handler_chain;

import com.liemartt.model.MatchScore;

public interface ScoreCalculationHandler {
    void setNext(ScoreCalculationHandler nextHandler);

    void handle(MatchScore match);
}
