package ua.training.controller.command.cashier;

import ua.training.controller.command.Command;
import ua.training.model.dao.CheckDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.entity.Check;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveCheck implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = (String) req.getSession().getAttribute("login");
        int userId = DaoFactory.getInstance().getUserDao().findUserByLogin(login).getId();
        Check check = (Check) req.getSession().getAttribute("check");
        check.setUserId(userId);
        CheckDao checkDao = DaoFactory.getInstance().getCheckDao();
        checkDao.createCheck(check);
        req.getSession().removeAttribute("check");
        return "/view/cashier/checkCreated.jsp";
    }
}
