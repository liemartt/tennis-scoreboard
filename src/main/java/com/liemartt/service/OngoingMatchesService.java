package com.liemartt.service;

import com.liemartt.model.MatchScore;
import com.liemartt.model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {
    private static final Map<UUID, MatchScore> ongoingMatches = new HashMap<>();

    public UUID addNewMatch(Player player1, Player player2) {
        MatchScore matchScore = new MatchScore(player1, player2);
        UUID uuid = matchScore.getUuid();
        ongoingMatches.put(uuid, matchScore);
        return uuid;
    }

    public MatchScore getMatch(UUID uuid) {
        return ongoingMatches.get(uuid);
    }
}
