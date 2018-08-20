package ua.training.controller.cashier;

import ua.training.model.dao.CheckDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.entity.Check;
import ua.training.model.services.CheckService;
import ua.training.model.utils.LocaleUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/saveCheck")
public class SaveCheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CheckService checkService = new CheckService();
        LocaleUtil localeUtil = new LocaleUtil((String) req.getSession().getAttribute("btnvalue"));
        String login = (String) req.getSession().getAttribute("login");
        int userId = DaoFactory.getInstance().getUserDao().findUserByLogin(login).getId();
        int checkId = checkService.getLatestId()+1;
        Check check = (Check) req.getSession().getAttribute("check");
        check.setUserId(userId);
        check.setId(checkId);
        CheckDao checkDao = DaoFactory.getInstance().getCheckDao();
        checkDao.createCheck(check);
        req.getSession().removeAttribute("check");
        resp.getWriter().write(localeUtil.getText("checkCreated"));
    }
}
