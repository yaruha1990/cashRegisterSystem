package ua.training.controller.command.admin;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.controller.listeners.SessionListener;
import ua.training.model.utils.LocaleUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminLogout implements Command {
    final static Logger logger = Logger.getLogger(AdminLogout.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        String sessionId = req.getParameter("sessionId");
        HttpSession session = SessionListener.sessionById.get(sessionId);
        logger.info("User's session with login "+session.getAttribute("login")+" has been stopped by admin");
        session.invalidate();
        return "redirect:"+localeUtilURL.getText("manageSessions");
    }
}
