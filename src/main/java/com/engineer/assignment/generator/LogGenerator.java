package com.engineer.assignment.generator;

import com.engineer.assignment.dao.LogDao;
import com.engineer.assignment.model.LogMessage;
import com.engineer.assignment.websocket.LogWebSocket;

import java.time.Instant;
import java.util.Random;


public class LogGenerator implements Runnable {


    private static final String[] APPS = {
            "PAYMENTS",
            "ORDERS",
            "AUTH",
            "INVENTORY"
    };


    private static final String[] MESSAGES = {
            "Operation completed successfully",
            "Connection timeout",
            "Invalid request received",
            "Retrying operation",
            "Unexpected response from downstream service"
    };


    private final LogDao dao;
    private final Random random = new Random();


    public LogGenerator(LogDao dao) {
        this.dao = dao;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000 + random.nextInt(3000));


                LogMessage log = new LogMessage(
                        Instant.now().getEpochSecond(),
                        APPS[random.nextInt(APPS.length)],
                        MESSAGES[random.nextInt(MESSAGES.length)]
                );


                dao.insert(log);
                LogWebSocket.broadcast(log);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}