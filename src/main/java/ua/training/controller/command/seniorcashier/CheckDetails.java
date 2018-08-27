package ua.training.controller.command.seniorcashier;

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

public class CheckDetails implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        CheckService checkService = new CheckService();
        CheckDao checkDao = DaoFactory.getInstance().getCheckDao();
        Check check = checkDao.findCheckById(Integer.valueOf(req.getParameter("checkId")));
        int checkSum = checkService.calculateCheckSum(check);
        req.setAttribute("checkSum",checkSum);
        req.setAttribute("check",check);
        return localeUtilURL.getText("checkDetails");
    }
}
