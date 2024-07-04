package com.liemartt.util;

import com.liemartt.model.Match;
import com.liemartt.model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DBInitializationListener implements ServletContextListener {
    public static void initDb() {
        SessionFactory tempSessionFactory = DBUtil.getSessionFactory();
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

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
