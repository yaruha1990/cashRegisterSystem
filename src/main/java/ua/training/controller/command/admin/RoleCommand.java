package ua.training.controller.command.admin;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.model.utils.LocaleUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoleCommand implements Command {
    final static Logger logger = Logger.getLogger(RoleCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        String userRole = (String) req.getSession().getAttribute("role");
        if (userRole.equals("admin")){
            logger.info("User "+req.getSession().getAttribute("login")+" has role "+userRole+" is logged in");
            return "redirect:"+localeUtilURL.getText("admin");
        }
        if (userRole.equals("merchant")){
            logger.info("User "+req.getSession().getAttribute("login")+" has role "+userRole+" is logged in");
            return "redirect:"+localeUtilURL.getText("merchant");
        }
        if (userRole.equals("cashier")){
            logger.info("User "+req.getSession().getAttribute("login")+" has role "+userRole+" is logged in");
            return "redirect:"+localeUtilURL.getText("cashier");
        }
        if (userRole.equals("seniorCashier")){
            logger.info("User "+req.getSession().getAttribute("login")+" has role "+userRole+" is logged in");
            return "redirect:"+localeUtilURL.getText("seniorCashier");
        }
        return "/WEB-INF/404.jsp";
    }
}
