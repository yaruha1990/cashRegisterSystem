package ua.training.controller.command.seniorcashier;

import ua.training.controller.command.Command;
import ua.training.model.dao.CheckDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.entity.Check;
import ua.training.model.utils.LocaleUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CheckSumReport implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        CheckDao checkDao = DaoFactory.getInstance().getCheckDao();
        List<Check> checks = checkDao.findAllSumReport(Integer.valueOf(req.getParameter("checkSumFrom")),Integer.valueOf(req.getParameter("checkSumTo")));
        req.setAttribute("checks",checks);
        return localeUtilURL.getText("checkSumReport");
    }
}
