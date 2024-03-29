package com.liemartt.service;

import com.liemartt.model.Match;
import com.liemartt.model.MatchScore;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {
    private static final Map<UUID, MatchScore> ongoingMatches = new HashMap<>();

    public static UUID addNewMatch(Match match) {
        MatchScore matchScore = new MatchScore(match);
        UUID uuid = UUID.randomUUID();
        ongoingMatches.put(uuid, matchScore);
        return uuid;
    }

    public static MatchScore getMatch(UUID uuid) {
        return ongoingMatches.get(uuid);
    }
}
