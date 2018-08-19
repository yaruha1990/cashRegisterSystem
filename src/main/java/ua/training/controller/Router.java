package ua.training.controller;

import ua.training.model.services.RegExp;
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

@WebServlet("/router")
public class Router extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LocaleUtil localeUtilURL = new LocaleUtil("url");

        String userRole = (String) req.getSession().getAttribute("role");

        if (userRole.equals("admin")){
            resp.sendRedirect(localeUtilURL.getText("admin"));
        }

        if (userRole.equals("merchant")){
            req.getRequestDispatcher("/productList").forward(req,resp);
        }

        if (userRole.equalsIgnoreCase("cashier")||userRole.equalsIgnoreCase("senior cashier")){
            resp.sendRedirect("/view/logout.jsp");
        }

    }

}
