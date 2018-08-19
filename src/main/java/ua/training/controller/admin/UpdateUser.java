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

@WebServlet("/updateUser")
public class UpdateUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        User user = userDao.findUserById(Integer.valueOf(req.getParameter("id")));
        req.setAttribute("user",user);
        req.getRequestDispatcher(localeUtilURL.getText("updateUser")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        int id = Integer.valueOf(req.getParameter("userId"));
        String login = req.getParameter("login");
        String password = req.getParameter("userPassword");
        String role = req.getParameter("role");
        User user = new User(id,login,password,role);
        userDao.updateUser(user);
        List<User> users = userDao.findAll();
        req.setAttribute("users",users);
        req.getRequestDispatcher(localeUtilURL.getText("manageAccounts")).forward(req,resp);
    }
}
