package com.liemartt.util;

import com.liemartt.model.Match;
import com.liemartt.model.Player;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBUtil {

    private static final Configuration configuration = new Configuration().addAnnotatedClass(Player.class).addAnnotatedClass(Match.class);

    @Getter
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        return configuration.buildSessionFactory();
    }

    public static void initDb(){
        SessionFactory tempSessionFactory = getSessionFactory();
        Session session = tempSessionFactory.getCurrentSession();
        session.beginTransaction();
        Player pl1;
        Player pl2;
        Match match;

        pl1 = new Player("Novak Djokovic");
        pl2 = new Player("Carlos Alcaraz");
        match = new Match(pl1, pl2, pl1);
        session.persist(pl1);
        session.persist(pl2);
        session.persist(match);

        pl1 = new Player("Jannik Sinner");
        pl2 = new Player("Daniil Medvedev");
        match = new Match(pl1, pl2, pl1);
        session.persist(pl1);
        session.persist(pl2);
        session.persist(match);

        pl1 = new Player("Alexander Zverev");
        pl2 = new Player("Andrey Rublev");
        match = new Match(pl1, pl2, pl2);
        session.persist(pl1);
        session.persist(pl2);
        session.persist(match);

        pl1 = new Player("Holger Rune");
        pl2 = new Player("Casper Ruud");
        match = new Match(pl1, pl2, pl2);
        session.persist(pl1);
        session.persist(pl2);
        session.persist(match);

        pl1 = new Player("Hubert Hurkacz");
        pl2 = new Player("Alex De Minaur");
        match = new Match(pl1, pl2, pl2);
        session.persist(pl1);
        session.persist(pl2);
        session.persist(match);

        session.getTransaction().commit();
    }

}