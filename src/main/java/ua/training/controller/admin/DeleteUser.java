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

@WebServlet("/deleteUser")
public class DeleteUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        int id = Integer.valueOf(req.getParameter("id"));
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        userDao.deleteUser(id);
        List<User> users = userDao.findAll();
        req.setAttribute("users",users);
        req.getRequestDispatcher(localeUtilURL.getText("manageAccounts")).forward(req,resp);
    }
}
