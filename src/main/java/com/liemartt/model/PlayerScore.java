package com.liemartt.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PlayerScore {
    private Player player;
    private int setCounter;
    private int gameCounter;
    private int pointCounter;
    private int tieBreaksCounter;
    private boolean advantage;

    public PlayerScore(Player player) {
        this.player = player;
    }

    public void addPoint() {
        int currentPoints = this.pointCounter;
        this.pointCounter = switch (currentPoints) {
            case 0 -> 15;
            case 15 -> 30;
            case 30 -> 40;
            default -> throw new IllegalStateException("Unexpected value: " + currentPoints);
        };
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

    public Long getId() {
        return player.getId();
    }

    public void resetTieBreaksCounter() {
        tieBreaksCounter = 0;
    }
}
