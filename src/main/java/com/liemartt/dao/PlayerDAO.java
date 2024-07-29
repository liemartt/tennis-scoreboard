package com.liemartt.dao;

import com.liemartt.model.Player;

import java.util.Optional;

public interface PlayerDAO {
    Optional<Player> getPlayerByName(String name);

    Player savePlayer(Player player);
}
