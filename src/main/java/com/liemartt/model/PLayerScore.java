package com.liemartt.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PLayerScore {
    private Player player;

    public PLayerScore(Player player) {
        this.player = player;
    }

    private int setCounter;
    private int gameCounter;
    private int pointCounter;
    private int tieBreaksCounter;
    private boolean advantage;

    public void addPoint() {
        pointCounter++;
    }

    public void addGame() {
        gameCounter++;
    }

    public void addTieBreak() {
        tieBreaksCounter++;
    }

    public void addSet() {
        setCounter++;
    }

    public void resetPointCounter() {
        pointCounter = 0;
    }
    public void resetGameCounter() {
        gameCounter = 0;
    }

    public String getName() {
        return player.getName();
    }
    public Long getId(){
        return player.getId();
    }

}
