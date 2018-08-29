package ua.training.controller.command.admin;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.utils.LocaleUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class UpdateUserPost implements Command {
    final static Logger logger = Logger.getLogger(UpdateUserPost.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        int id = Integer.valueOf(req.getParameter("userId"));
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        User user = new User(id,login,password,role);
        logger.info("Admin has started to update user.");
        logger.info("New user's credentials are "+id+" "+login+" "+password+" "+role);
        userDao.updateUser(user);
        List<User> users = userDao.findAll();
        req.setAttribute("users",users);
        return localeUtilURL.getText("manageAccounts");
    }
}
