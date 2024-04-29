package com.liemartt;

import com.liemartt.model.Match;
import com.liemartt.model.MatchScore;
import com.liemartt.model.Player;
import com.liemartt.service.MatchScoreCalculationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MatchScoreCalculationServiceTest {
    @Test
    public void gameNotEndedWhenPlayer1ScoresAt40_40() {
        Player player1 = new Player("firstPLayer");
        Player player2 = new Player("secondPLayer");
        MatchScore matchScore = new MatchScore(new Match(player1, player2));
        for (int i = 0; i < 3; i++) {
            MatchScoreCalculationService.addPointToPlayer(matchScore, player1);
            MatchScoreCalculationService.addPointToPlayer(matchScore, player2);
        }
        MatchScoreCalculationService.addPointToPlayer(matchScore, player1);
        Assertions.assertEquals(matchScore.getFirstPlayerScore().getGameCounter(), 0);
        Assertions.assertEquals(matchScore.getSecondPlayerScore().getGameCounter(), 0);
    }

    @Test
    public void gameEndedWhenPlayer1ScoresAt40_0() {
        Player player1 = new Player("firstPLayer");
        Player player2 = new Player("secondPLayer");
        MatchScore matchScore = new MatchScore(new Match(player1, player2));
        for (int i = 0; i < 3; i++) {
            MatchScoreCalculationService.addPointToPlayer(matchScore, player1);
        }
        MatchScoreCalculationService.addPointToPlayer(matchScore, player1);
        Assertions.assertEquals(matchScore.getFirstPlayerScore().getGameCounter(), 1);
        Assertions.assertEquals(matchScore.getSecondPlayerScore().getGameCounter(), 0);
    }
    @Test
    public void gameSwitchesToTieBreakAt6_6() {
        Player player1 = new Player("firstPLayer");
        Player player2 = new Player("secondPLayer");
        MatchScore matchScore = new MatchScore(new Match(player1, player2));
        for (int i = 0; i < 20; i++) {
            MatchScoreCalculationService.addPointToPlayer(matchScore, player2);
        }
        for (int i = 0; i < 24; i++) {
            MatchScoreCalculationService.addPointToPlayer(matchScore, player1);
        }
        for (int i = 0; i < 4; i++) {
            MatchScoreCalculationService.addPointToPlayer(matchScore, player2);
        }
        Assertions.assertTrue(matchScore.isTieBreak());
    }
    @Test
    public void playerWinsMatchByWinningTwoSets() {
        Player player1 = new Player("firstPLayer");
        Player player2 = new Player("secondPLayer");
        MatchScore matchScore = new MatchScore(new Match(player1, player2));
        for (int i = 0; i < 54; i++) {
            MatchScoreCalculationService.addPointToPlayer(matchScore, player1);
        }
        Assertions.assertEquals(matchScore.getWinner(), matchScore.getFirstPlayerScore());
    }
    @Test
    public void playerWinsSetAtScore7_5() {
        Player player1 = new Player("firstPLayer");
        Player player2 = new Player("secondPLayer");
        MatchScore matchScore = new MatchScore(new Match(player1, player2));
        for (int i = 0; i < 20; i++) {
            MatchScoreCalculationService.addPointToPlayer(matchScore, player1);
        }
        for (int i = 0; i < 20; i++) {
            MatchScoreCalculationService.addPointToPlayer(matchScore, player2);
        }
        for (int i = 0; i < 8; i++) {
            MatchScoreCalculationService.addPointToPlayer(matchScore, player1);
        }
        Assertions.assertEquals(matchScore.getFirstPlayerScore().getSetCounter(), 1);
    }
    @Test
    public void playerLosesAtScore40_40WithAdvantage() {
        Player player1 = new Player("firstPLayer");
        Player player2 = new Player("secondPLayer");
        MatchScore matchScore = new MatchScore(new Match(player1, player2));
        for (int i = 0; i < 3; i++) {
            MatchScoreCalculationService.addPointToPlayer(matchScore, player1);
            MatchScoreCalculationService.addPointToPlayer(matchScore, player2);
        }
        MatchScoreCalculationService.addPointToPlayer(matchScore, player1);

        MatchScoreCalculationService.addPointToPlayer(matchScore, player2);
        MatchScoreCalculationService.addPointToPlayer(matchScore, player2);
        MatchScoreCalculationService.addPointToPlayer(matchScore, player2);


        Assertions.assertEquals(matchScore.getSecondPlayerScore().getGameCounter(), 1);
    }
    @Test
    public void playerWinsTieBreakAtScore7_5() {
        Player player1 = new Player("firstPLayer");
        Player player2 = new Player("secondPLayer");
        MatchScore matchScore = new MatchScore(new Match(player1, player2));
        for (int i = 0; i < 24; i++) {
            MatchScoreCalculationService.addPointToPlayer(matchScore, player1);
        }
        for (int i = 0; i < 24; i++) {
            MatchScoreCalculationService.addPointToPlayer(matchScore, player2);
        }
        for (int i = 0; i < 5; i++) {
            MatchScoreCalculationService.addPointToPlayer(matchScore, player1);
            MatchScoreCalculationService.addPointToPlayer(matchScore, player2);
        }
        MatchScoreCalculationService.addPointToPlayer(matchScore, player1);
        MatchScoreCalculationService.addPointToPlayer(matchScore, player1);

        Assertions.assertEquals(matchScore.getFirstPlayerScore().getSetCounter(), 1);
    }
}
