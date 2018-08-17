package ua.training.controller;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class SessionListener implements HttpSessionListener {

    public static Map<String, HttpSession> sessionById = new HashMap<String, HttpSession>();

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        sessionById.put(httpSessionEvent.getSession().getId(), httpSessionEvent.getSession());
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        Map<HttpSession,String> sessions = (HashMap<HttpSession,String>)httpSessionEvent.getSession().getServletContext().getAttribute("sessions");
        sessions.remove(httpSessionEvent.getSession());
        sessionById.remove(httpSessionEvent.getSession().getId());
    }
}
