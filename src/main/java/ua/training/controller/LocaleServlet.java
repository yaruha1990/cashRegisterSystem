package ua.training.controller;

import ua.training.model.utils.LocaleUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/locale")
public class LocaleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        String jspName = req.getHeader("referer");

        LocaleUtil localeUtil = new LocaleUtil(req.getParameter("btn"));
        resp.sendRedirect(jspName);

    }
}
