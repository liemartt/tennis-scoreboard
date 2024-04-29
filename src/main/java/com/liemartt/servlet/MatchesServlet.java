package com.liemartt.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.liemartt.dao.MatchDAO;
import com.liemartt.dao.MatchDAOImpl;
import com.liemartt.dao.PlayerDAOImpl;
import com.liemartt.model.Match;
import com.liemartt.model.Player;
import com.liemartt.util.ThymeleafUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

@WebServlet(urlPatterns = "/matches")
public class MatchesServlet extends HttpServlet {
    private static final int MATCHES_PER_PAGE = 5;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MatchDAO matchDAO = new MatchDAOImpl();
        ServletContext servletContext = getServletContext();
        TemplateEngine templateEngine = ThymeleafUtil.getTemplateEngine(servletContext);
        WebContext context = ThymeleafUtil.getWebContext(req, resp, servletContext);

        String page = req.getParameter("page");
        String playerName = req.getParameter("filter_by_player_name");
        long numberOfPages = 1L;
        Optional<Player> player = Optional.empty();
        if (playerName != null) {
            player = new PlayerDAOImpl().getPlayerByName(playerName);
        }
        if (player.isPresent()) {
            numberOfPages = Math.ceilDiv(matchDAO.getNumberOfMatches(player.get()), MATCHES_PER_PAGE);
        } else numberOfPages = Math.ceilDiv(matchDAO.getNumberOfMatches(), MATCHES_PER_PAGE);
        int numOfPage = 1;
        if (page != null) {
            try {
                numOfPage = Integer.parseInt(page);
            } catch (NumberFormatException ignored) {
            }
        }
        if (numOfPage < 1 || numOfPage > numberOfPages) {
            numOfPage = 1;
        }
        List<Match> matchList;
        if (player.isPresent()) {
            matchList = matchDAO.getMatchesByPageByPlayer(numOfPage, player.get());
        } else matchList = matchDAO.getMatchesByPage(numOfPage);

        context.setVariable("matches", matchList);
        context.setVariable("filter_by_player_name", player.isPresent() ? player.get().getName() : "");
        context.setVariable("numberOfPages", numberOfPages);
        templateEngine.process("matches.html", context, resp.getWriter());
    }

}
