package com.liemartt.dao;

import com.liemartt.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerDAO {
    Optional<Player> getPlayerByName(String name);
    Player addNewPlayer(String name);
    List<Player> getAllPlayers();

}
