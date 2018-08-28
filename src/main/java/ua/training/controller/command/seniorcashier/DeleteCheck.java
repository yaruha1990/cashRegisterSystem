package ua.training.controller.command.seniorcashier;

import ua.training.controller.command.Command;
import ua.training.model.dao.CheckDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.entity.Check;
import ua.training.model.entity.User;
import ua.training.model.utils.LocaleUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class DeleteCheck implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        CheckDao checkDao = DaoFactory.getInstance().getCheckDao();
        checkDao.deleteCheck(Integer.valueOf(req.getParameter("checkId")));
        int page = 1;
        int recordsPerPage = 2;
        int numberOfRecords = checkDao.getNumberOfRecords();
        int numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
        List<Check> checks = checkDao.findAll(recordsPerPage,(page-1)*recordsPerPage);
        req.setAttribute("numberOfPages", numberOfPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("checks",checks);
        return localeUtilURL.getText("checkList");
    }
}
