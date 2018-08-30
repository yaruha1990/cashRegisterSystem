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

public class CheckList implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        int page = 1;
        int recordsPerPage = 2;
        if(req.getParameter("page") != null){
            try {
                page = Integer.parseInt(req.getParameter("page"));
            }catch (NumberFormatException e){
                throw new RuntimeException("Not numeric value in \'page\' parameter");
            }
        }
        CheckDao checkDao = DaoFactory.getInstance().getCheckDao();
        int numberOfRecords = checkDao.getNumberOfRecords();
        int numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
        if (page < 1) page = 1;
        if (page > numberOfPages) page = numberOfPages;
        List<Check> checks = checkDao.findAll(recordsPerPage,(page-1)*recordsPerPage);
        req.setAttribute("numberOfPages", numberOfPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("checks",checks);
        return localeUtilURL.getText("checkList");
    }
}
