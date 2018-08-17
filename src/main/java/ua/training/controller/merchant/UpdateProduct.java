package ua.training.controller.merchant;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ProductDao;
import ua.training.model.entity.Product;
import ua.training.model.utils.LocaleUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/updateProduct")
public class UpdateProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDao = DaoFactory.getInstance().getProductDao();
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        Product product = productDao.findProductById(Integer.valueOf(req.getParameter("id")));
        req.setAttribute("product",product);
        req.getRequestDispatcher(localeUtilURL.getText("updateProduct")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocaleUtil localeUtilURL = new LocaleUtil("url");

        ProductDao productDao = DaoFactory.getInstance().getProductDao();
        int id = Integer.valueOf(req.getParameter("productId"));
        String vendorCode = req.getParameter("vendorCode");
        String productName = req.getParameter("productName");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        Product product = new Product(id,vendorCode,productName,price,quantity);
        productDao.updateProduct(product);
        List<Product> products = productDao.findAll();
        req.setAttribute("products",products);
        req.getRequestDispatcher(localeUtilURL.getText("merchant")).forward(req,resp);
    }
}
