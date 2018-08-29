package ua.training.controller.filters;

import ua.training.model.services.RegExp;
import ua.training.model.utils.LocaleUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/app/createProduct","/app/updateProductPost"})
public class ProductCreationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        RegExp regExp = new RegExp();
        String userRole = (String) req.getSession().getAttribute("role");

        if (userRole == null || !userRole.equals("merchant")){
            servletResponse.setContentType("text/html");
            servletResponse.getWriter().write("Page is unavailable because your user role is not \'merchant\'<br><a href=\"/\">To index page</a>");
            return;
        }

        LocaleUtil localeUtil = new LocaleUtil((String) req.getSession().getAttribute("btnvalue"));

        String vendorCode = req.getParameter("vendorCode");
        String productName = req.getParameter("productName");
        String price = req.getParameter("price");
        String quantity = req.getParameter("quantity");

        if (vendorCode.equals("")){
            servletResponse.getWriter().write(localeUtil.getText("emptyVendorCode"));
            return;
        }
        if (productName.equals("")){
            servletResponse.getWriter().write(localeUtil.getText("emptyProductName"));
            return;
        }
        if (price.equals("")){
            servletResponse.getWriter().write(localeUtil.getText("emptyPrice"));
            return;
        }
        if (quantity.equals("")){
            servletResponse.getWriter().write(localeUtil.getText("emptyQuantity"));
            return;
        }
        if (!regExp.isValidInput(RegExp.VENDOR_CODE_REGEXP,vendorCode)){
            servletResponse.getWriter().write(localeUtil.getText("invalidCharsVendorCode"));
            return;
        }
        if (!regExp.isValidInput(RegExp.LETTERS_DIGITS_REGEXP,productName)){
            servletResponse.getWriter().write(localeUtil.getText("invalidCharsProductName"));
            return;
        }
        filterChain.doFilter(req,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
