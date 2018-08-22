package ua.training.controller.filters;

import ua.training.model.services.RegExp;
import ua.training.model.services.UserIdentificationHelper;
import ua.training.model.utils.LocaleUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter("/app/router")
public class UserIdentificationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        RegExp regExp = new RegExp();
        UserIdentificationHelper userIdentificationHelper = new UserIdentificationHelper();
        LocaleUtil localeUtil = new LocaleUtil((String) request.getSession().getAttribute("btnvalue"));

        String login = request.getParameter("login");
        String password = request.getParameter("password");

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
        if (!userIdentificationHelper.isUserExist(login)) {
            servletResponse.getWriter().write(localeUtil.getText("loginNotExist"));
            return;
        }
        if (!userIdentificationHelper.isUserValid(login,password)){
            servletResponse.getWriter().write(localeUtil.getText("incorrectLogOrPass"));
            return;
        }

        String userRole = userIdentificationHelper.getRoleByLogin(login);

        Map<HttpSession,String> sessions = (HashMap<HttpSession,String>)request.getServletContext().getAttribute("sessions");

        if (sessions.containsKey(request.getSession())){
            servletResponse.getWriter().println(localeUtil.getText("incompleteSession"));
            servletResponse.getWriter().println("Active user is <u>" + sessions.get(request.getSession()) + "</u><br>");
            servletResponse.getWriter().println(localeUtil.getText("toIndexPage"));
            return;
        }
        if (sessions.containsValue(login)){
            servletResponse.getWriter().println(localeUtil.getText("loginAlreadyInWork"));
            return;
        }
        sessions.put(request.getSession(),login);

        request.getSession().setAttribute("login",login);
        request.getSession().setAttribute("role",userRole);

        filterChain.doFilter(request,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
