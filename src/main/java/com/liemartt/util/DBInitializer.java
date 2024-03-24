package com.liemartt.util;

import com.liemartt.model.Match;
import com.liemartt.model.Player;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBInitializer {
    @Getter
    private static final SessionFactory sessionFactory;


    static {
        Configuration configuration = new Configuration().addAnnotatedClass(Player.class).addAnnotatedClass(Match.class);
        sessionFactory = configuration.buildSessionFactory();
    }
}