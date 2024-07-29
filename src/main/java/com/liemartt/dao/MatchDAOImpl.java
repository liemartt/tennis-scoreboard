package com.liemartt.dao;

import com.liemartt.dto.MatchesRequestDTO;
import com.liemartt.dto.MatchesResponseDTO;
import com.liemartt.model.Match;
import com.liemartt.model.Player;
import com.liemartt.util.DBUtil;
import org.hibernate.Session;
import org.hibernate.query.Page;

import java.util.List;

public class MatchDAOImpl implements MatchDAO {


    @Override
    public MatchesResponseDTO getFilteredMatches(MatchesRequestDTO dto) {
        Session session = DBUtil.getSession();
        MatchesResponseDTO responseDTO = new MatchesResponseDTO();
        List<Match> matches;
        Player player = dto.getPlayer();
        long totalMatches = dto.getTotalMatches();
        long page = dto.getPage();
        long matchesPerPage = dto.getMatchesPerPage();
        if ((page - 1) * matchesPerPage >= totalMatches) page = 1;
        if (player != null) {
            session.beginTransaction();
            matches =
                    session
                            .createSelectionQuery("FROM Match where player1=:player or player2=:player", Match.class)
                            .setParameter("player", player)
                            .setPage(Page.page((int) matchesPerPage, (int) page - 1))
                            .getResultList();
            session.getTransaction().commit();
            totalMatches = matches.size();
        } else {
            session.beginTransaction();
            matches =
                    session
                            .createSelectionQuery("FROM Match", Match.class)
                            .setPage(Page.page((int) matchesPerPage, (int) page - 1))
                            .getResultList();
            session.getTransaction().commit();
        }
        responseDTO.setMatches(matches);
        responseDTO.setPage(page);
        responseDTO.setTotalPages(Math.ceilDiv(totalMatches, matchesPerPage));
        return responseDTO;
    }

    @Override
    public long getMatchesCount() {
        Session session = DBUtil.getSession();
        session.beginTransaction();
        long total = session.createSelectionQuery("FROM Match", Match.class).stream().count();
        session.getTransaction().commit();
        return total;
    }

    @Override
    public Match saveMatch(Match match) {
        Session session = DBUtil.getSession();
        session.beginTransaction();
        session.persist(match);
        session.getTransaction().commit();
        return match;
    }
}
