package ua.training.controller.command.cashier;

import ua.training.controller.command.Command;
import ua.training.model.dao.CheckDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.entity.Check;
import ua.training.model.services.CheckService;
import ua.training.model.utils.LocaleUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveCheck implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CheckService checkService = new CheckService();
        String login = (String) req.getSession().getAttribute("login");
        int userId = DaoFactory.getInstance().getUserDao().findUserByLogin(login).getId();
        int checkId = checkService.getLatestId()+1;
        Check check = (Check) req.getSession().getAttribute("check");
        check.setUserId(userId);
        check.setId(checkId);
        CheckDao checkDao = DaoFactory.getInstance().getCheckDao();
        checkDao.createCheck(check);
        req.getSession().removeAttribute("check");
        return "/view/cashier/checkCreated.jsp";
    }
}
