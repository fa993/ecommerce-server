package com.fa993.controllers;

import com.fa993.configs.GetHttpSessionConfigurator;
import com.fa993.pojos.ECommerceUser;
import com.fa993.pojos.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint(value = "/notify", configurator = GetHttpSessionConfigurator.class)
public class NotificationEndpoint {

    private static Logger logger = LoggerFactory.getLogger(NotificationEndpoint.class);

    private static ObjectMapper obm = new ObjectMapper();

    private static Map<String, Session> sessions = new HashMap<>();
    private static Map<String, String> sessionIdVsUser = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        Principal principal = (Principal) config.getUserProperties()
                .get(HttpSession.class.getName());
        UsernamePasswordAuthenticationToken u = (UsernamePasswordAuthenticationToken) principal;
        ECommerceUser us = (ECommerceUser) u.getPrincipal();
        sessions.put(us.getId(), session);
        sessionIdVsUser.put(session.getId(), us.getId());
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        String userId = sessionIdVsUser.remove(session.getId());
        sessions.remove(userId);
    }

    public static void sendToUser(String userId, Order order) {
        try {
            sessions.get(userId).getAsyncRemote().sendText(obm.writeValueAsString(order));
        } catch (Exception ex) {
            logger.info("Failed to send message");
        }
    }

}
