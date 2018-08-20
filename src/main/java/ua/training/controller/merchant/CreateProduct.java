package ua.training.controller.merchant;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ProductDao;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.Product;
import ua.training.model.entity.User;
import ua.training.model.utils.LocaleUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/createProduct")
public class CreateProduct extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        String vendorCode = req.getParameter("vendorCode");
        String productName = req.getParameter("productName");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.valueOf(req.getParameter("quantity"));
        ProductDao productDao = DaoFactory.getInstance().getProductDao();
        Product product = new Product(vendorCode,productName,price,quantity);
        productDao.createProduct(product);
        List<Product> products = productDao.findAll();
        req.setAttribute("products",products);
        req.getRequestDispatcher(localeUtilURL.getText("merchant")).forward(req,resp);
    }
}
