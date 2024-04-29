package com.liemartt.servlet;
import com.liemartt.util.DBUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DBUtil.initDb();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("exited");
    }
}
