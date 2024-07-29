package com.liemartt.servlet;

import com.liemartt.dao.MatchDAO;
import com.liemartt.dao.MatchDAOImpl;
import com.liemartt.dao.PlayerDAOImpl;
import com.liemartt.dto.MatchesRequestDTO;
import com.liemartt.dto.MatchesResponseDTO;
import com.liemartt.model.Match;
import com.liemartt.model.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = "/matches")
public class MatchesServlet extends AbstractServlet {
    private static final int MATCHES_PER_PAGE = 5;
    private final MatchDAO matchDAO = new MatchDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initializeServlet(req, resp);

        String page = req.getParameter("page");
        String playerName = req.getParameter("filter_by_player_name");

        MatchesRequestDTO requestDTO = new MatchesRequestDTO();

        Optional<Player> player = new PlayerDAOImpl().getPlayerByName(playerName);
        player.ifPresent(requestDTO::setPlayer);
        try {
            long numOfPage = Integer.parseInt(page);
            requestDTO.setPage(numOfPage);
        } catch (Exception e) {
            requestDTO.setPage(1);
        }
        requestDTO.setTotalMatches(matchDAO.getMatchesCount());
        requestDTO.setMatchesPerPage(MATCHES_PER_PAGE);
        MatchesResponseDTO responseDTO = matchDAO.getFilteredMatches(requestDTO);
        List<Match> matchList = responseDTO.getMatches();
        context.setVariable("matches", matchList);
        context.setVariable("filter_by_player_name", player.isPresent() ? player.get().getName() : "");
        context.setVariable("numberOfPages", responseDTO.getTotalPages());
        templateEngine.process("matches.html", context, resp.getWriter());
    }

}
