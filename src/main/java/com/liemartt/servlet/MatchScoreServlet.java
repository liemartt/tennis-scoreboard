package com.liemartt.servlet;

import com.liemartt.dao.PlayerDAO;
import com.liemartt.dao.PlayerDAOImpl;
import com.liemartt.model.MatchScore;
import com.liemartt.model.Player;
import com.liemartt.service.FinishedMatchesPersistenceService;
import com.liemartt.service.MatchScoreCalculationService;
import com.liemartt.service.OngoingMatchesService;
import com.liemartt.util.ThymeleafUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = "/match-score")
public class MatchScoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        TemplateEngine templateEngine = ThymeleafUtil.getTemplateEngine(servletContext);
        WebContext context = ThymeleafUtil.getWebContext(req, resp, servletContext);
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        MatchScore matchScore = OngoingMatchesService.getMatch(uuid);
        context.setVariable("matchScore", matchScore);
        templateEngine.process("match-score", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PlayerDAO playerDAO = new PlayerDAOImpl();
        ServletContext servletContext = getServletContext();
        TemplateEngine templateEngine = ThymeleafUtil.getTemplateEngine(servletContext);
        WebContext context = ThymeleafUtil.getWebContext(req, resp, servletContext);

        String id = req.getParameter("id");
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        MatchScore matchScore = OngoingMatchesService.getMatch(uuid);
        //TODO check if match exists
        Player winner = (matchScore.getPlayer1().getId().toString().equals(id) ? matchScore.getPlayer1() : matchScore.getPlayer2()).getPlayer();
        MatchScoreCalculationService calculationService = new MatchScoreCalculationService(matchScore);
        //TODO singleton
        calculationService.addPointToPlayer(winner);
        if (matchScore.isFinished()) {
            context.setVariable("winner", matchScore.getWinner().getName());
            context.setVariable("uuid", uuid);
            FinishedMatchesPersistenceService.saveMatch(matchScore.getMatch());

            templateEngine.process("finishedMatch", context, resp.getWriter());
        }
        else this.doGet(req, resp);

    }
}
