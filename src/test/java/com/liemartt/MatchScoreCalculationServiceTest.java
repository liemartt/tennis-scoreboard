package com.liemartt;

import com.liemartt.model.MatchScore;
import com.liemartt.model.Player;
import com.liemartt.service.MatchScoreCalculationService;
import com.liemartt.service.handler_chain.ChainBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MatchScoreCalculationServiceTest {
    MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService(ChainBuilder.buildChain());

    @Test
    public void gameNotEndedWhenPlayer1ScoresAt40_40() {
        Player player1 = new Player("firstPLayer");
        Player player2 = new Player("secondPLayer");
        MatchScore matchScore = new MatchScore(player1, player2);
        for (int i = 0; i < 3; i++) {
            matchScore.setPointWinner(matchScore.getPlayer1());
            matchScoreCalculationService.execute(matchScore);
            matchScore.setPointWinner(matchScore.getPlayer2());
            matchScoreCalculationService.execute(matchScore);
        }
        matchScore.setPointWinner(matchScore.getPlayer1());
        matchScoreCalculationService.execute(matchScore);
        Assertions.assertEquals(matchScore.getPlayer1Score().getGameCounter(), 0);
        Assertions.assertEquals(matchScore.getPlayer2Score().getGameCounter(), 0);
    }

    @Test
    public void gameEndedWhenPlayer1ScoresAt40_0() {
        Player player1 = new Player("firstPLayer");
        Player player2 = new Player("secondPLayer");
        MatchScore matchScore = new MatchScore(player1, player2);
        for (int i = 0; i < 3; i++) {
            matchScore.setPointWinner(matchScore.getPlayer1());
            matchScoreCalculationService.execute(matchScore);
        }
        matchScoreCalculationService.execute(matchScore);
        Assertions.assertEquals(matchScore.getPlayer1Score().getGameCounter(), 1);
        Assertions.assertEquals(matchScore.getPlayer2Score().getGameCounter(), 0);
    }

    @Test
    public void gameSwitchesToTieBreakAt6_6() {
        Player player1 = new Player("firstPLayer");
        Player player2 = new Player("secondPLayer");
        MatchScore matchScore = new MatchScore(player1, player2);
        for (int i = 0; i < 20; i++) {
            matchScore.setPointWinner(matchScore.getPlayer2());
            matchScoreCalculationService.execute(matchScore);
        }
        for (int i = 0; i < 24; i++) {
            matchScore.setPointWinner(matchScore.getPlayer1());
            matchScoreCalculationService.execute(matchScore);
        }
        for (int i = 0; i < 4; i++) {
            matchScore.setPointWinner(matchScore.getPlayer2());
            matchScoreCalculationService.execute(matchScore);
        }
        Assertions.assertTrue(matchScore.isTieBreak());
    }

    @Test
    public void playerWinsMatchByWinningTwoSets() {
        Player player1 = new Player("firstPLayer");
        Player player2 = new Player("secondPLayer");
        MatchScore matchScore = new MatchScore(player1, player2);
        for (int i = 0; i < 54; i++) {
            matchScore.setPointWinner(matchScore.getPlayer1());
            matchScoreCalculationService.execute(matchScore);
        }
        Assertions.assertEquals(matchScore.getWinner(), matchScore.getPlayer1Score());
    }

    @Test
    public void playerWinsSetAtScore7_5() {
        Player player1 = new Player("firstPLayer");
        Player player2 = new Player("secondPLayer");
        MatchScore matchScore = new MatchScore(player1, player2);
        for (int i = 0; i < 20; i++) {
            matchScore.setPointWinner(matchScore.getPlayer1());
            matchScoreCalculationService.execute(matchScore);
        }
        for (int i = 0; i < 20; i++) {
            matchScore.setPointWinner(matchScore.getPlayer2());
            matchScoreCalculationService.execute(matchScore);
        }
        for (int i = 0; i < 8; i++) {
            matchScore.setPointWinner(matchScore.getPlayer1());
            matchScoreCalculationService.execute(matchScore);
        }
        Assertions.assertEquals(matchScore.getPlayer1Score().getSetCounter(), 1);
    }

    @Test
    public void playerLosesAtScore40_40WithAdvantage() {
        Player player1 = new Player("firstPLayer");
        Player player2 = new Player("secondPLayer");
        MatchScore matchScore = new MatchScore(player1, player2);
        for (int i = 0; i < 3; i++) {
            matchScore.setPointWinner(matchScore.getPlayer1());
            matchScoreCalculationService.execute(matchScore);
            matchScore.setPointWinner(matchScore.getPlayer2());
            matchScoreCalculationService.execute(matchScore);
        }
        matchScore.setPointWinner(matchScore.getPlayer1());
        matchScoreCalculationService.execute(matchScore);

        matchScore.setPointWinner(matchScore.getPlayer2());
        matchScoreCalculationService.execute(matchScore);
        matchScoreCalculationService.execute(matchScore);
        matchScoreCalculationService.execute(matchScore);


        Assertions.assertEquals(matchScore.getPlayer2Score().getGameCounter(), 1);
    }

    @Test
    public void playerWinsTieBreakAtScore7_5() {
        Player player1 = new Player("firstPLayer");
        Player player2 = new Player("secondPLayer");
        MatchScore matchScore = new MatchScore(player1, player2);
        for (int i = 0; i < 24; i++) {
            matchScore.setPointWinner(matchScore.getPlayer1());
            matchScoreCalculationService.execute(matchScore);
        }
        for (int i = 0; i < 24; i++) {
            matchScore.setPointWinner(matchScore.getPlayer2());
            matchScoreCalculationService.execute(matchScore);
        }
        for (int i = 0; i < 5; i++) {
            matchScore.setPointWinner(matchScore.getPlayer1());
            matchScoreCalculationService.execute(matchScore);
            matchScore.setPointWinner(matchScore.getPlayer2());
            matchScoreCalculationService.execute(matchScore);
        }
        matchScore.setPointWinner(matchScore.getPlayer1());
        matchScoreCalculationService.execute(matchScore);
        matchScoreCalculationService.execute(matchScore);

        Assertions.assertEquals(matchScore.getPlayer1Score().getSetCounter(), 1);
    }
}
