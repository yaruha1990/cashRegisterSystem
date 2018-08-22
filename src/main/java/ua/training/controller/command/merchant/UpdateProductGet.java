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

public class UpdateProductGet implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDao = DaoFactory.getInstance().getProductDao();
        LocaleUtil localeUtilURL = new LocaleUtil("url");
        System.out.println(req.getParameter("id"));
        Product product = productDao.findProductById(Integer.valueOf(req.getParameter("id")));
        req.setAttribute("product",product);
        return localeUtilURL.getText("updateProduct");
    }
}
