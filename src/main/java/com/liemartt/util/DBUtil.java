package com.liemartt.util;

import com.liemartt.model.Match;
import com.liemartt.model.Player;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBUtil {

    private static final Configuration configuration = new Configuration().addAnnotatedClass(Player.class).addAnnotatedClass(Match.class);

    @Getter
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        return configuration.buildSessionFactory();
    }
}