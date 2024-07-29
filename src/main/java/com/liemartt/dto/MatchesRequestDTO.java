package com.liemartt.dto;

import com.liemartt.model.Player;
import lombok.Data;

@Data
public class MatchesRequestDTO {
    private Player player;
    private long page;
    private long matchesPerPage;
    private long totalMatches;
}
