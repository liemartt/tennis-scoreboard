package com.liemartt.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MatchDAO matchDAO = new MatchDAOImpl();
        ServletContext servletContext = getServletContext();
        TemplateEngine templateEngine = ThymeleafUtil.getTemplateEngine(servletContext);
        WebContext context = ThymeleafUtil.getWebContext(req, resp, servletContext);
        String page = req.getParameter("page");
        Long numberOfPages = Math.ceilDiv(matchDAO.getNumberOfMatches(), 5);
        int numOfPage = 1;
        if (page != null) {
            try {
                numOfPage = Integer.parseInt(page);
                if (numOfPage < 1|| numOfPage > numberOfPages) {
                    numOfPage = 1;
                }
            } catch (NumberFormatException ignored) {
            }
        }
        List<Match> matchList = matchDAO.getMatchesByPage(numOfPage);
        System.out.println(matchList);
        context.setVariable("matches", matchList);
        context.setVariable("playerName", "");
        context.setVariable("numberOfPages", numberOfPages);
        templateEngine.process("matches.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        TemplateEngine templateEngine = ThymeleafUtil.getTemplateEngine(servletContext);
        WebContext context = ThymeleafUtil.getWebContext(req, resp, servletContext);
        String playerName = req.getParameter("playerName");
        Optional<Player> player = new PlayerDAOImpl().getPlayerByName(playerName);
        List<Match> matchList = new ArrayList<>();
        if (player.isPresent()) {
            matchList = new MatchDAOImpl().getMatchesByPlayer(player.get());
        }

        //TODO page DTO
        else context.setVariable("error", "Player not found!");
        context.setVariable("matches", matchList);
        context.setVariable("playerName", playerName);
        templateEngine.process("matches.html", context, resp.getWriter());
    }
}
