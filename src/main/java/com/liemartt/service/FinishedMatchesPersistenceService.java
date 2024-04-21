package com.liemartt.service;

import com.liemartt.model.Match;
import com.liemartt.model.MatchScore;
import com.liemartt.model.Player;
import com.liemartt.util.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class FinishedMatchesPersistenceService {
    private final static SessionFactory sessionFactory = DBUtil.getSessionFactory();
    public static void saveMatch(Match match) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(match);
        session.getTransaction().commit();
    }
}
