package com.liemartt.dao;

import com.liemartt.model.Player;
import com.liemartt.util.DBUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class PlayerDAOImpl implements PlayerDAO {

    @Override
    public Optional<Player> getPlayerByName(String name) {
        try (Session session = DBUtil.getSession()) {
            session.beginTransaction();
            List<Player> players =
                    session.createSelectionQuery("FROM Player WHERE name = :name", Player.class)
                            .setParameter("name", name)
                            .getResultList();
            session.getTransaction().commit();
            return players.isEmpty() ? Optional.empty() : Optional.of(players.get(0));
        }
    }

    @Override
    public Player savePlayer(Player player) {
        try (Session session = DBUtil.getSession()) {
            session.beginTransaction();
            session.persist(player);
            session.getTransaction().commit();
            return player;
        }
    }

}
