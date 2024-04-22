package com.liemartt.dao;

import com.liemartt.model.Match;
import com.liemartt.model.Player;

import java.util.List;

public interface MatchDAO {
    List<Match> getAllMatches();
    List<Match> getMatchesByPlayer(Player player);
}
