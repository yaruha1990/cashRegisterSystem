package ua.training.controller.command.merchant;

import org.apache.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ProductDao;
import ua.training.model.dao.impl.UserDaoImpl;
import ua.training.model.entity.Product;
import ua.training.model.utils.LocaleUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class CreateProduct implements Command {
    final static Logger logger = Logger.getLogger(CreateProduct.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        String vendorCode = req.getParameter("vendorCode");
        String productName = req.getParameter("productName");
        int price = Integer.parseInt(req.getParameter("price"));
        int quantity = Integer.valueOf(req.getParameter("quantity"));
        ProductDao productDao = DaoFactory.getInstance().getProductDao();
        Product product = new Product(vendorCode,productName,price,quantity);
        logger.info("Merchant "+req.getSession().getAttribute("login")+" started to create new product");
        logger.info("Product's credentials are "+vendorCode+" "+productName+" "+price+" "+quantity);
        productDao.createProduct(product);
        List<Product> products = productDao.findAll();
        req.setAttribute("products",products);
        return localeUtilURL.getText("productList");
    }
}
