package ua.training.controller.command.admin;

import ua.training.controller.command.Command;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.utils.LocaleUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteUser implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        int id = Integer.valueOf(req.getParameter("id"));
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        userDao.deleteUser(id);
        List<User> users = userDao.findAll();
        req.setAttribute("users",users);
        return localeUtilURL.getText("manageAccounts");
    }
}