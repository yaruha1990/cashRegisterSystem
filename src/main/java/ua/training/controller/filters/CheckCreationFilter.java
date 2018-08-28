package ua.training.controller.filters;

import ua.training.model.services.ProductService;
import ua.training.model.services.RegExp;
import ua.training.model.utils.LocaleUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;

@WebFilter("/app/addProductToCheck")
public class CheckCreationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        ProductService productService = new ProductService();
        RegExp regExp = new RegExp();
        String userRole = (String) req.getSession().getAttribute("role");

        if (userRole == null || !userRole.equals("cashier")){
            servletResponse.setContentType("text/html");
            servletResponse.getWriter().write("Page is unavailable because your user role is not \'cashier\'<br><a href=\"/\">To index page</a>");
            return;
        }
        LocaleUtil localeUtil = new LocaleUtil((String) req.getSession().getAttribute("btnvalue"));

        String vendorCode = req.getParameter("vendorCode");
        String quantity = req.getParameter("quantity");

        if (vendorCode.equals("")){
            servletResponse.getWriter().write(localeUtil.getText("emptyVendorCode"));
            return;
        }
        if (quantity.equals("")){
            servletResponse.getWriter().write(localeUtil.getText("emptyQuantity"));
            return;
        }

        if (!regExp.isValidInput(RegExp.LETTERS_DIGITS_REGEXP,vendorCode)){
            servletResponse.getWriter().write(localeUtil.getText("invalidCharsVendorCode"));
            return;
        }

        if (!productService.isProductAvailableByVendorCode(vendorCode)) {
            throw new RuntimeException("Such vendor code doesn't exist");
        }
        filterChain.doFilter(req,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
