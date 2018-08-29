package ua.training.controller.command.admin;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Logout implements Command {
    final static Logger logger = Logger.getLogger(Logout.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("login") != null) logger.info("User "+req.getSession().getAttribute("login")+" has logged out");
        req.getSession().invalidate();
        return "redirect:/";
    }
}
