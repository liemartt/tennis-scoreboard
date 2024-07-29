package com.liemartt.dao;

import com.liemartt.dto.MatchesRequestDTO;
import com.liemartt.dto.MatchesResponseDTO;
import com.liemartt.model.Match;

public interface MatchDAO {
    MatchesResponseDTO getFilteredMatches(MatchesRequestDTO dto);

    long getMatchesCount();

    Match saveMatch(Match match);
}
