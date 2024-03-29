package com.liemartt.model;

public class MatchScore {
    private Match match;
    private boolean isFinished;
    private int[] counterOfSets;
    private int[] counterOfGames;
    private int[] counterOfPoints;
    private int[] counterOfTieBreaks;
    private boolean[] advantage;

    public MatchScore(Match match) {
        this.match = match;
        this.isFinished = false;
        this.counterOfSets = new int[]{0, 0};
        this.counterOfGames = new int[]{0, 0};
        this.counterOfPoints = new int[]{0, 0};
        this.counterOfTieBreaks = new int[]{0, 0};
        this.advantage = new boolean[]{false, false};
    }

    public void addPointToPlayer(Player player) {
        int ally;
        int enemy;
        if (player.equals(getPlayer1())) {
            ally = 0;
            enemy = 1;
        } else {
            enemy = 0;
            ally = 1;
        }
        if (counterOfPoints[ally] <= 2) counterOfPoints[ally] += 1;
        else if (counterOfPoints[ally] == 3 && counterOfPoints[enemy] <= 2) {
            addGameToPlayer(ally, enemy);
            counterOfPoints = new int[]{0, 0};
        } else if (counterOfPoints[ally] == 3 && advantage[ally]) {
            addGameToPlayer(ally, enemy);
            counterOfPoints = new int[]{0, 0};
        } else {
            if (advantage[enemy]) advantage[enemy] = false;
            else advantage[ally] = true;
        }
        System.out.println("player1: " + counterOfGames[0] + ", player2: " + counterOfGames[1]);
        System.out.println("player1: " + counterOfPoints[0] + ", player2: " + counterOfPoints[1]);
    }

    private void addGameToPlayer(int ally, int enemy) {

    }

    public Player getPlayer1() {
        return match.getPlayer1();
    }

    public Player getPlayer2() {
        return match.getPlayer2();
    }

}
