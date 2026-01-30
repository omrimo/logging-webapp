package com.engineer.assignment.listener;


import com.engineer.assignment.dao.LogDao;
import com.engineer.assignment.generator.LogGenerator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class AppStartupListener implements ServletContextListener {


    private Thread generatorThread;


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LogDao dao = new LogDao();
        generatorThread = new Thread(new LogGenerator(dao));
        generatorThread.setDaemon(true);
        generatorThread.start();
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (generatorThread != null) {
            generatorThread.interrupt();
        }
    }
}