package ua.training.controller.command.admin;

import ua.training.controller.command.Command;
import ua.training.model.utils.LocaleUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoleCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        String userRole = (String) req.getSession().getAttribute("role");
        if (userRole.equals("admin")){
            return "redirect:"+localeUtilURL.getText("admin");
        }
        if (userRole.equals("merchant")){
            return "/app/products";
        }
        if (userRole.equals("cashier")){
            return "redirect:"+localeUtilURL.getText("cashier");
        }
        return "/WEB-INF/404.jsp";
    }
}
