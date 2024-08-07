package com.liemartt.servlet;

import com.liemartt.dao.PlayerDAO;
import com.liemartt.dao.PlayerDAOImpl;
import com.liemartt.model.Player;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@WebServlet(urlPatterns = "/new-match")
public class NewMatchServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        initializeServlet(req, resp);
        templateEngine.process("new-match", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PlayerDAO playerDAO = new PlayerDAOImpl();
        initializeServlet(req, resp);

        String firstPlayerName = req.getParameter("player1_name");
        String secondPlayerName = req.getParameter("player2_name");

        if (Objects.equals(firstPlayerName, "") || Objects.equals(secondPlayerName, "") || Objects.equals(firstPlayerName, secondPlayerName)) {
            context.setVariable("error", "Invalid player name");
            templateEngine.process("new-match", context, resp.getWriter());
            return;
        }

        Player firstPlayer =
                playerDAO
                        .getPlayerByName(firstPlayerName)
                        .orElseGet(() -> playerDAO.savePlayer(new Player(firstPlayerName)));
        Player secondPlayer =
                playerDAO
                        .getPlayerByName(secondPlayerName)
                        .orElseGet(() -> playerDAO.savePlayer(new Player(secondPlayerName)));

        UUID uuid = ongoingMatchesService.addNewMatch(firstPlayer, secondPlayer);
        resp.sendRedirect("/match-score?uuid=" + uuid.toString());
    }
}
