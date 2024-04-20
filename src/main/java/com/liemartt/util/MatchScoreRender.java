package com.liemartt.util;

import com.liemartt.model.MatchScore;
import org.thymeleaf.context.WebContext;

import java.util.UUID;

public class MatchScoreRender {
    public static void render(WebContext context, MatchScore matchScore, UUID uuid){
        context.setVariable("uuid", uuid);
        context.setVariable("player1Name", matchScore.getPlayer1().getName());
        context.setVariable("player2Name", matchScore.getPlayer2().getName());
        context.setVariable("player1Id", matchScore.getPlayer1().getId());
        context.setVariable("player2Id", matchScore.getPlayer2().getId());
        context.setVariable("player1Sets", matchScore.getPlayer1().getSetCounter());
        context.setVariable("player2Sets", matchScore.getPlayer2().getSetCounter());
        context.setVariable("player1Games", matchScore.getPlayer1().getGameCounter());
        context.setVariable("player2Games", matchScore.getPlayer2().getGameCounter());
        context.setVariable("player1Points", matchScore.getPlayer1().getPointCounter());
        context.setVariable("player2Points", matchScore.getPlayer2().getPointCounter());
        context.setVariable("player1TieBreaks", matchScore.getPlayer1().getTieBreaksCounter());
        context.setVariable("player2TieBreaks", matchScore.getPlayer2().getTieBreaksCounter());
    }
}