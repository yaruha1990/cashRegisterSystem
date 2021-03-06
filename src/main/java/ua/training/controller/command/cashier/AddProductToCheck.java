package ua.training.controller.command.cashier;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.model.dao.DaoFactory;
import ua.training.model.entity.Check;
import ua.training.model.entity.Product;
import ua.training.model.services.CheckService;
import ua.training.model.services.ProductService;
import ua.training.model.utils.LocaleUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class AddProductToCheck implements Command {
    private Check check;
    final static Logger logger = Logger.getLogger(AddProductToCheck.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocaleUtil localeUtil = new LocaleUtil((String) req.getSession().getAttribute("btnvalue"));
        ProductService productService = new ProductService();
        CheckService checkService = new CheckService();
        String vendorCode = req.getParameter("vendorCode");
        int quantity = Integer.valueOf(req.getParameter("quantity"));
        if (!productService.isProductAvailableByQuantity(vendorCode,quantity)){
            resp.getWriter().write(localeUtil.getText("cantAddProduct")+productService.getProductQuantityInStock(vendorCode)+"<br>");
            resp.getWriter().write(localeUtil.getText("historyBack"));
            logger.warn("Product "+vendorCode+" is not available by quantity in stock");
            throw  new RuntimeException("Product is not available by quantity");
        }
        check = (Check) req.getSession().getAttribute("check");
        if (check == null) {
            check = new Check();
            logger.info("New check is opened");
        }
        Product product = DaoFactory.getInstance().getProductDao().findProductByVendorCode(vendorCode);
        int checkId = checkService.getLatestId();
        check.setId(checkId);
        checkService.addProductToCheck(check,product,quantity);
        check.setCheckSum(checkService.calculateCheckSum(check));
        check.setDate(LocalDate.now());
        check.setDateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        int checkSum = checkService.calculateCheckSum(check);
        req.getSession().setAttribute("checkSum",checkSum);
        req.getSession().setAttribute("check",check);
        return "redirect:/view/cashier/openCheck.jsp";
    }


}
