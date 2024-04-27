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
    public List<Match> getMatchesByPage(int page) {
        SessionFactory sessionFactory = DBUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Match");
        query.setMaxResults(page*5);
        List<Match> matches = query.getResultList();
        matches = matches.subList((page-1)*5, Math.min(matches.size(), page*5));
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
        System.out.println(matches);
        session.getTransaction().commit();
        return matches;
    }

    @Override
    public long getNumberOfMatches() {
        SessionFactory sessionFactory = DBUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql = "select count(*) from Match";
        Query query = session.createQuery(hql);
        long numberOfMatches = (Long)query.uniqueResult();
        session.getTransaction().commit();
        return numberOfMatches;
    }

    @Override
    public void saveMatch(Match match) {
        SessionFactory sessionFactory = DBUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(match);
        session.getTransaction().commit();
    }
}
