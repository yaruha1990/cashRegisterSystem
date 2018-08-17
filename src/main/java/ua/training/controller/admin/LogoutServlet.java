package ua.training.controller.admin;

import ua.training.controller.SessionListener;
import ua.training.model.utils.LocaleUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/adminLogout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        String sessionId = req.getParameter("sessionId");
        HttpSession session = SessionListener.sessionById.get(sessionId);
        session.invalidate();
        resp.sendRedirect(localeUtilURL.getText("manageSessions"));
    }
}
