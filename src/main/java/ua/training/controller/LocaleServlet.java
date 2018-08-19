package ua.training.controller;

import ua.training.model.utils.LocaleUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/locale")
public class LocaleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocaleUtil localeUtil = new LocaleUtil(req.getParameter("btn"));
        req.getSession().setAttribute("loaded","true");
        req.getSession().setAttribute("btnvalue",req.getParameter("btn"));
        req.getSession().setAttribute("title",localeUtil.getText("title"));
        req.getSession().setAttribute("loginAsBtnName",localeUtil.getText("loginAsBtnName"));
        req.getSession().setAttribute("passwordAsBtnName",localeUtil.getText("passwordAsBtnName"));
        req.getSession().setAttribute("signin",localeUtil.getText("signin"));
        req.getSession().setAttribute("hello",localeUtil.getText("hello"));
        req.getSession().setAttribute("logout",localeUtil.getText("logout"));
        req.getSession().setAttribute("administratorPage",localeUtil.getText("administratorPage"));
        req.getSession().setAttribute("manageSessions",localeUtil.getText("manageSessions"));
        req.getSession().setAttribute("manageAccounts",localeUtil.getText("manageAccounts"));
        req.getSession().setAttribute("sessionIdAsLabelName",localeUtil.getText("sessionIdAsLabelName"));
        req.getSession().setAttribute("back",localeUtil.getText("back"));
        req.getSession().setAttribute("stopSession",localeUtil.getText("stopSession"));
        req.getSession().setAttribute("toAdminPage",localeUtil.getText("toAdminPage"));
        req.getSession().setAttribute("usersList",localeUtil.getText("usersList"));
        req.getSession().setAttribute("id",localeUtil.getText("id"));
        req.getSession().setAttribute("roleAsBtnName",localeUtil.getText("roleAsBtnName"));
        req.getSession().setAttribute("deleteAsBtnName",localeUtil.getText("deleteAsBtnName"));
        req.getSession().setAttribute("updateAsBtnName",localeUtil.getText("updateAsBtnName"));
        req.getSession().setAttribute("createUser",localeUtil.getText("createUser"));
        req.getSession().setAttribute("setRole",localeUtil.getText("setRole"));
        req.getSession().setAttribute("admin",localeUtil.getText("admin"));
        req.getSession().setAttribute("cashier",localeUtil.getText("cashier"));
        req.getSession().setAttribute("seniorCashier",localeUtil.getText("seniorCashier"));
        req.getSession().setAttribute("merchant",localeUtil.getText("merchant"));
        req.getSession().setAttribute("create",localeUtil.getText("create"));
        req.getSession().setAttribute("updateUser",localeUtil.getText("updateUser"));
        req.getSession().setAttribute("merchantPage",localeUtil.getText("merchantPage"));
        req.getSession().setAttribute("productsInStock",localeUtil.getText("productsInStock"));
        req.getSession().setAttribute("vendorCode",localeUtil.getText("vendorCode"));
        req.getSession().setAttribute("productName",localeUtil.getText("productName"));
        req.getSession().setAttribute("price",localeUtil.getText("price"));
        req.getSession().setAttribute("quantity",localeUtil.getText("quantity"));
        req.getSession().setAttribute("createProduct",localeUtil.getText("createProduct"));
        req.getSession().setAttribute("updateProduct",localeUtil.getText("updateProduct"));
        req.getSession().setAttribute("toProductList",localeUtil.getText("toProductList"));
        resp.sendRedirect("/");
    }
}
