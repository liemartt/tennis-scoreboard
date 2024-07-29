package com.liemartt.dto;

import com.liemartt.model.Match;
import lombok.Data;

import java.util.List;

@Data
public class MatchesResponseDTO {
    private long page;
    private long totalPages;
    private List<Match> matches;
}