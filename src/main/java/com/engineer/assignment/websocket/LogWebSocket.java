package com.engineer.assignment.websocket;

import com.engineer.assignment.model.LogMessage;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/ws/logs")
public class LogWebSocket {

    private static final Set<Session> sessions = new CopyOnWriteArraySet<>();

    public static void broadcast(LogMessage log) {
        String json = String.format(
                "{\"timestamp\":\"%d\",\"app\":\"%s\",\"message\":\"%s\"}",
                log.getTimestamp(), log.getApp(), log.getMessage()
        );

        for (Session s : sessions) {
            try {
                s.getBasicRemote().sendText(json);
            } catch (IOException ignored) {
            }
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }
}