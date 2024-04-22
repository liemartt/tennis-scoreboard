package com.liemartt.dao;

import com.liemartt.model.Match;
import com.liemartt.model.Player;
import com.liemartt.util.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class MatchDAOImpl implements MatchDAO{
    @Override
    public List<Match> getAllMatches() {
        SessionFactory sessionFactory = DBUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Match> matches = session.createQuery("from Match").getResultList();
        session.getTransaction().commit();
        return matches;
    }

    @Override
    public List<Match> getMatchesByPlayer(Player player) {
        SessionFactory sessionFactory = DBUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql = "from Match m where m.player1 = :player or m.player2 = :player";
        Query query = session.createQuery(hql);
        List<Match> matches = query.setParameter("player", player).getResultList();
        session.getTransaction().commit();
        return matches;
    }
}
