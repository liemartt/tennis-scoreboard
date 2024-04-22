package com.liemartt.servlet;

import com.liemartt.dao.PlayerDAO;
import com.liemartt.dao.PlayerDAOImpl;
import com.liemartt.model.Match;
import com.liemartt.model.Player;
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
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@WebServlet(urlPatterns = "/new-match")
public class NewMatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        TemplateEngine templateEngine = ThymeleafUtil.getTemplateEngine(servletContext);
        WebContext context = ThymeleafUtil.getWebContext(req, resp, servletContext);
        templateEngine.process("new-match", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PlayerDAO playerDAO = new PlayerDAOImpl();
        ServletContext servletContext = getServletContext();
        TemplateEngine templateEngine = ThymeleafUtil.getTemplateEngine(servletContext);
        WebContext context = ThymeleafUtil.getWebContext(req, resp, servletContext);

        String firstPlayerName = req.getParameter("player1_name");
        String secondPlayerName = req.getParameter("player2_name");

        if(Objects.equals(firstPlayerName, "") || Objects.equals(secondPlayerName, "")) {
            context.setVariable("error", "Invalid player name");
            templateEngine.process("new-match", context, resp.getWriter());
            return;
        }

        Player firstPlayer = playerDAO.getPlayerByName(firstPlayerName).orElseGet(() -> playerDAO.addNewPlayer(firstPlayerName));
        Player secondPlayer = playerDAO.getPlayerByName(secondPlayerName).orElseGet(() -> playerDAO.addNewPlayer(secondPlayerName));

        Match match = new Match(firstPlayer, secondPlayer);
        UUID uuid = OngoingMatchesService.addNewMatch(match);
        resp.sendRedirect("/match-score?uuid=" + uuid.toString());
    }
}
