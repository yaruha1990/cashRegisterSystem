package ua.training.controller.admin;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.utils.LocaleUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/manageAccount")
public class AccountManageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        List<User> users = userDao.findAll();
        req.setAttribute("users",users);
        req.getRequestDispatcher(localeUtilURL.getText("manageAccounts")).forward(req,resp);
    }
}
