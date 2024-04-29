package com.liemartt.dao;

import com.liemartt.model.Match;
import com.liemartt.model.Player;

import java.util.List;

public interface MatchDAO {
    List<Match> getMatchesByPage(int page);
    List<Match> getMatchesByPageByPlayer(int page, Player player);
    long getNumberOfMatches();
    long getNumberOfMatches(Player player);
    void saveMatch(Match match);
}
