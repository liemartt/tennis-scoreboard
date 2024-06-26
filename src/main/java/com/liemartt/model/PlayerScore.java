package com.liemartt.model;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class PlayerScore {
    private Player player;

    public PlayerScore(Player player) {
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

    public void resetTieBreaksCounter() {
        tieBreaksCounter = 0;
    }
}
