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

public class UpdateUserGet implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        User user = userDao.findUserById(Integer.valueOf(req.getParameter("id")));
        req.setAttribute("user",user);
        return localeUtilURL.getText("updateUser");
    }
}
