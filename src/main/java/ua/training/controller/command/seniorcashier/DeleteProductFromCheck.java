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
import java.sql.Connection;

public class DeleteProductFromCheck implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        CheckService checkService = new CheckService();
        int checkId = Integer.valueOf(req.getParameter("checkId"));
        int productId = Integer.valueOf(req.getParameter("productId"));
        CheckDao checkDao = DaoFactory.getInstance().getCheckDao();
        checkDao.deleteProductFromCheck(checkId, productId);
        Check check = checkDao.findCheckById(checkId);
        int checkSum = checkService.calculateCheckSum(check);
        req.setAttribute("checkSum",checkSum);
        req.setAttribute("check",check);
        return localeUtilURL.getText("checkDetails");
    }
}
