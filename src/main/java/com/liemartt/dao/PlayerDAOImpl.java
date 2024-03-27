package com.liemartt.dao;

import com.liemartt.model.Player;
import com.liemartt.util.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PlayerDAOImpl implements PlayerDAO {
    SessionFactory sessionFactory = DBUtil.getSessionFactory();

    @Override
    public Optional<Player> getPlayerByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql = "FROM Player WHERE name = :name";
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        List<Player> players = query.getResultList();
        session.getTransaction().commit();
        return players.isEmpty() ? Optional.empty() : Optional.of(players.get(0));
    }

    @Override
    public Player addNewPlayer(String name) {
        Session session = sessionFactory.getCurrentSession();
        Player player = new Player(name);
        session.beginTransaction();
        session.persist(player);
        session.getTransaction().commit();
        return player;
    }

    @Override
    public List<Player> getAllPlayers() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Player> players = session.createQuery("FROM Player").getResultList();
        session.getTransaction().commit();
        return players;
    }
}
