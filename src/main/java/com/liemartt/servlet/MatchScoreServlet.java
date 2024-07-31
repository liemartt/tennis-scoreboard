package com.liemartt.servlet;

import com.liemartt.dao.MatchDAO;
import com.liemartt.dao.MatchDAOImpl;
import com.liemartt.model.Match;
import com.liemartt.model.MatchScore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = "/match-score")
public class MatchScoreServlet extends AbstractServlet {
    private final MatchDAO matchDAO = new MatchDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initializeServlet(req, resp);
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        MatchScore matchScore = ongoingMatchesService.getMatch(uuid);
        if (matchScore == null) {
            context.setVariable("error", "No such match found");
            templateEngine.process("new-match", context, resp.getWriter());
            return;
        }
        context.setVariable("matchScore", matchScore);
        templateEngine.process("match-score", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initializeServlet(req, resp);
        String id = req.getParameter("id");
        long winnerId;
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        MatchScore matchScore = ongoingMatchesService.getMatch(uuid);

        if (matchScore == null) {
            context.setVariable("error", "No such match found");
            templateEngine.process("new-match", context, resp.getWriter());
            return;
        }

        try {
            winnerId = Long.parseLong(id);
        } catch (Exception ignored) {
            context.setVariable("error", "Incorrect player id");
            templateEngine.process("new-match", context, resp.getWriter());
            return;
        }

        if (matchScore.getPlayer1().getId().equals(winnerId)) {
            matchScore.setPointWinner(matchScore.getPlayer1());
        } else if (matchScore.getPlayer2().getId().equals(winnerId)) {
            matchScore.setPointWinner(matchScore.getPlayer2());
        } else {
            context.setVariable("error", "No such player in this match");
            templateEngine.process("new-match", context, resp.getWriter());
            return;
        }

        matchScoreCalculationService.execute(matchScore);

        if (matchScore.getWinner() != null) {
            context.setVariable("winner", matchScore.getWinner().getName());
            context.setVariable("uuid", uuid);
            Match match = new Match(
                    matchScore.getPlayer1Score().getPlayer(),
                    matchScore.getPlayer2Score().getPlayer(),
                    matchScore.getWinner().getPlayer());
            matchDAO.saveMatch(match);
            templateEngine.process("finishedMatch", context, resp.getWriter());
        } else this.doGet(req, resp);
    }

}

