package ua.training.controller;

import ua.training.model.services.UserIdentificationHelper;
import ua.training.model.utils.LocaleUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/identification")
public class UserIdentificationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserIdentificationHelper userIdentificationHelper = new UserIdentificationHelper();

        if (!userIdentificationHelper.isUserExist(login)) {
            resp.getWriter().println("Such username "+login+" doesn't exist.<br>");
            resp.getWriter().println("<input type=\"button\" value=\"Back\" onclick=\"history.back()\">");
            return;
        }
        if (!userIdentificationHelper.isUserValid(login,password)){
            resp.getWriter().println("Incorrect username or password.<br>");
            resp.getWriter().println("<input type=\"button\" value=\"Back\" onclick=\"history.back()\">");
            return;
        }

        Map<HttpSession,String> sessions = (HashMap<HttpSession,String>)req.getServletContext().getAttribute("sessions");

        if (sessions.containsKey(req.getSession())){
            resp.getWriter().println("You have one incomplete session.<br>");
            resp.getWriter().println("Active user is <u>" + sessions.get(req.getSession()) + "</u><br>");
            resp.getWriter().println("<input type=\"button\" value=\"Back\" onclick=\"history.back()\">");
            return;
        }
        if (sessions.containsValue(login)){
            resp.getWriter().println("Such login is already in work <br>");
            resp.getWriter().println("<input type=\"button\" value=\"Back\" onclick=\"history.back()\">");
            return;
        }
        sessions.put(req.getSession(),login);

        String userRole = userIdentificationHelper.getRoleByLogin(login);
        req.getSession().setAttribute("login",login);
        req.getSession().setAttribute("role",userRole);

        if (userRole.equals("admin")){
            resp.sendRedirect(localeUtilURL.getText("admin"));
        }

        if (userRole.equals("merchant")){
            resp.sendRedirect("/productList");
        }

        if (userRole.equalsIgnoreCase("cashier")||userRole.equalsIgnoreCase("senior cashier")){
            resp.sendRedirect("/view/logout.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("/");
    }
}
