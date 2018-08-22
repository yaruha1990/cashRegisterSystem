package ua.training.controller.command.admin;

import ua.training.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexPage implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/index.jsp";
    }
}
