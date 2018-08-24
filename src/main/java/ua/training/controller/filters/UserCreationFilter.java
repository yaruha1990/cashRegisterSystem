package ua.training.controller.filters;

import ua.training.model.services.RegExp;
import ua.training.model.utils.LocaleUtil;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/app/createUser", "/app/updateUserPost"})
public class UserCreationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        RegExp regExp = new RegExp();
        String userRole = (String) req.getSession().getAttribute("role");

        if (userRole == null || !userRole.equals("admin")){
            servletResponse.getWriter().write("Page is unavailable because your user role is not \'admin\'<br><a href=\"/\">To index page</a>");
            return;
        }
        LocaleUtil localeUtil = new LocaleUtil((String) req.getSession().getAttribute("btnvalue"));

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login.equals("")){
            servletResponse.getWriter().write(localeUtil.getText("emptyLogin"));
            return;
        }
        if (password.equals("")){
            servletResponse.getWriter().write(localeUtil.getText("emptyPassword"));
            return;
        }

        if (!regExp.isValidInput(RegExp.LETTERS_DIGITS_REGEXP,login)){
            servletResponse.getWriter().write(localeUtil.getText("invalidCharsLogin"));
            return;
        }
        if (!regExp.isValidInput(RegExp.LETTERS_DIGITS_REGEXP,password)){
            servletResponse.getWriter().write(localeUtil.getText("invalidCharsPassword"));
            return;
        }
        filterChain.doFilter(req,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
