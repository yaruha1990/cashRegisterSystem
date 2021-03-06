package ua.training.controller.command.seniorcashier;

import org.apache.log4j.Logger;
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
import java.sql.Connection;

public class CheckDetails implements Command {
    final static Logger logger = Logger.getLogger(CheckDetails.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        CheckService checkService = new CheckService();
        CheckDao checkDao = DaoFactory.getInstance().getCheckDao();
        logger.info("User "+req.getSession().getAttribute("login")+" started to view details of check with id "+req.getParameter("checkId"));
        Check check = checkDao.findCheckById(Integer.valueOf(req.getParameter("checkId")));
        int checkSum = checkService.calculateCheckSum(check);
        req.setAttribute("checkSum",checkSum);
        req.setAttribute("check",check);
        return localeUtilURL.getText("checkDetails");
    }
}
