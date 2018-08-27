package ua.training.controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/app/checkOperations")
public class CheckOperationsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest.getParameter("checkOperation").equals("Details")){
            servletRequest.setAttribute("path","checkDetails");
        }
        if (servletRequest.getParameter("checkOperation").equals("Delete")){
            servletRequest.setAttribute("path","deleteCheck");
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}

