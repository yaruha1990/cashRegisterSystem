package ua.training.controller.command.merchant;

import ua.training.controller.command.Command;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ProductDao;
import ua.training.model.entity.Product;
import ua.training.model.utils.LocaleUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductsList implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        ProductDao productDao = DaoFactory.getInstance().getProductDao();
        List<Product> products = productDao.findAll();
        req.setAttribute("products",products);
        return localeUtilURL.getText("merchant");
    }
}
