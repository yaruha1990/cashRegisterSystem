package ua.training.controller.listeners;

import ua.training.model.dao.DaoFactory;
import ua.training.model.utils.LocaleUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class ContextListener implements ServletContextListener {

    private Map<HttpSession, String> sessions;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        sessions = new HashMap<HttpSession, String>();
        servletContext.setAttribute("sessions",sessions);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        sessions = null;
    }
}
