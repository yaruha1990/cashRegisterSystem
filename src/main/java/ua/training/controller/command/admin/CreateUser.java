package ua.training.controller.command.admin;

import ua.training.controller.command.Command;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.utils.LocaleUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.List;

public class CreateUser implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        User user = new User(login,password,role);
        userDao.createUser(user);
        List<User> users = userDao.findAll();
        req.setAttribute("users",users);
        return localeUtilURL.getText("manageAccounts");
    }
}
