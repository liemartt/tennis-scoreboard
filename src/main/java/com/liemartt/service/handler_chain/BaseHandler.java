package com.liemartt.service.handler_chain;

public abstract class BaseHandler implements ScoreCalculationHandler {
    protected ScoreCalculationHandler nextHandler;

    @Override
    public void setNext(ScoreCalculationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
