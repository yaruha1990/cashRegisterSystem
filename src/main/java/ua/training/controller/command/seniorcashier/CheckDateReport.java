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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CheckDateReport implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate checkDateFrom = LocalDate.parse(req.getParameter("checkDateFrom"), formatter);
        LocalDate checkDateTo = LocalDate.parse(req.getParameter("checkDateTo"), formatter);
        CheckDao checkDao = DaoFactory.getInstance().getCheckDao();
        List<Check> checks = checkDao.findAll(checkDateFrom,checkDateTo);
        req.setAttribute("checks",checks);
        return localeUtilURL.getText("checkDateReport");
    }
}
