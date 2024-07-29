package com.liemartt.util;

import com.liemartt.model.Match;
import com.liemartt.model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBUtil {

    private static final Configuration configuration = new Configuration().addAnnotatedClass(Player.class).addAnnotatedClass(Match.class);

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        return configuration.buildSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}