package ua.training.controller.command.utils;

import ua.training.controller.command.Command;
import ua.training.model.utils.LocaleUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Locale implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        req.getSession().setAttribute("cashierPage",localeUtil.getText("cashierPage"));
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
        req.getSession().setAttribute("openChekBtnName",localeUtil.getText("openChekBtnName"));
        req.getSession().setAttribute("toCashierPage",localeUtil.getText("toCashierPage"));
        req.getSession().setAttribute("addProduct",localeUtil.getText("addProduct"));
        req.getSession().setAttribute("toCheckPage",localeUtil.getText("toCheckPage"));
        req.getSession().setAttribute("cantAddProduct",localeUtil.getText("cantAddProduct"));
        req.getSession().setAttribute("historyBack",localeUtil.getText("historyBack"));
        req.getSession().setAttribute("saveCheck",localeUtil.getText("saveCheck"));
        req.getSession().setAttribute("checkCreated",localeUtil.getText("checkCreated"));
        req.getSession().setAttribute("checkSumAsLabel",localeUtil.getText("checkSumAsLabel"));
        req.getSession().setAttribute("grn",localeUtil.getText("grn"));
        req.getSession().setAttribute("pieces",localeUtil.getText("pieces"));
        req.getSession().setAttribute("seniorCashierPage",localeUtil.getText("seniorCashierPage"));
        req.getSession().setAttribute("checkListBtnName",localeUtil.getText("checkListBtnName"));
        req.getSession().setAttribute("checkSumBtnName",localeUtil.getText("checkSumBtnName"));
        req.getSession().setAttribute("checkDateBtnName",localeUtil.getText("checkDateBtnName"));
        req.getSession().setAttribute("toSeniorCashierPage",localeUtil.getText("toSeniorCashierPage"));
        req.getSession().setAttribute("checkListTblName",localeUtil.getText("checkListTblName"));
        req.getSession().setAttribute("checkId",localeUtil.getText("checkId"));
        req.getSession().setAttribute("createdBy",localeUtil.getText("createdBy"));
        req.getSession().setAttribute("checkSumAsTblHeader",localeUtil.getText("checkSumAsTblHeader"));
        req.getSession().setAttribute("createdAt",localeUtil.getText("createdAt"));
        req.getSession().setAttribute("checkDetails",localeUtil.getText("checkDetails"));
        req.getSession().setAttribute("show",localeUtil.getText("show"));
        req.getSession().setAttribute("delete",localeUtil.getText("delete"));
        req.getSession().setAttribute("deleteCheck",localeUtil.getText("deleteCheck"));
        req.getSession().setAttribute("deleteProduct",localeUtil.getText("deleteProduct"));
        req.getSession().setAttribute("productId",localeUtil.getText("productId"));
        return "redirect:/";
    }
}
