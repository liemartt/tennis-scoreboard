package com.liemartt.servlet;

import com.liemartt.dao.MatchDAOImpl;
import com.liemartt.model.MatchScore;
import com.liemartt.model.Player;
import com.liemartt.service.MatchScoreCalculationService;
import com.liemartt.service.OngoingMatchesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = "/match-score")
public class MatchScoreServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initializeServlet(req, resp);
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        MatchScore matchScore = OngoingMatchesService.getMatch(uuid);
        System.out.println(matchScore);
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
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        MatchScore matchScore = OngoingMatchesService.getMatch(uuid);

        if (matchScore == null) {
            context.setVariable("error", "No such match found");
            templateEngine.process("new-match", context, resp.getWriter());
            return;
        }

        Player winner = this.getWinner(id, matchScore);
        MatchScoreCalculationService.addPointToPlayer(matchScore, winner);

        if (matchScore.isFinished()) {
            context.setVariable("winner", matchScore.getWinner().getName());
            context.setVariable("uuid", uuid);
            new MatchDAOImpl().saveMatch(matchScore.getMatch());
            templateEngine.process("finishedMatch", context, resp.getWriter());
        } else this.doGet(req, resp);
    }

    private Player getWinner(String winnerId, MatchScore matchScore) {
        if (matchScore.getFirstPlayerScore().getId().toString().equals(winnerId)) {
            return matchScore.getFirstPlayerScore().getPlayer();
        } else return matchScore.getSecondPlayerScore().getPlayer();
    }
}

