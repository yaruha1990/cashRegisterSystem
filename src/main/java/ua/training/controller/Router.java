package ua.training.controller;

import ua.training.controller.command.*;
import ua.training.controller.command.admin.*;
import ua.training.controller.command.cashier.AddProductToCheck;
import ua.training.controller.command.cashier.SaveCheck;
import ua.training.controller.command.merchant.CreateProduct;
import ua.training.controller.command.merchant.ProductsList;
import ua.training.controller.command.merchant.UpdateProductGet;
import ua.training.controller.command.merchant.UpdateProductPost;
import ua.training.controller.command.seniorcashier.CheckDetails;
import ua.training.controller.command.seniorcashier.CheckList;
import ua.training.controller.command.seniorcashier.DeleteCheck;
import ua.training.controller.command.seniorcashier.DeleteProductFromCheck;
import ua.training.controller.command.utils.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/app/*")
public class Router extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init() throws ServletException {
        commands.put("router",new RoleCommand());
        commands.put("adminLogout",new AdminLogout());
        commands.put("logout",new Logout());
        commands.put("manageAccounts",new ManageAccounts());
        commands.put("createUser",new CreateUser());
        commands.put("deleteUser",new DeleteUser());
        commands.put("updateUserGet",new UpdateUserGet());
        commands.put("updateUserPost",new UpdateUserPost());
        commands.put("products", new ProductsList());
        commands.put("createProduct", new CreateProduct());
        commands.put("updateProductGet", new UpdateProductGet());
        commands.put("updateProductPost", new UpdateProductPost());
        commands.put("addProductToCheck", new AddProductToCheck());
        commands.put("saveCheck", new SaveCheck());
        commands.put("locale", new Locale());
        commands.put("checkList", new CheckList());
        commands.put("deleteCheck", new DeleteCheck());
        commands.put("checkDetails", new CheckDetails());
        commands.put("deleteProductFromCheck", new DeleteProductFromCheck());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI().replaceAll(".*app/","");
        if (req.getAttribute("path") != null){
            path = (String)(req.getAttribute("path"));
        }
        Command command = commands.getOrDefault(path,new IndexPage());
        String page = command.execute(req,resp);
        if(page.contains("redirect")){
            resp.sendRedirect(page.replace("redirect:", ""));
        }else {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }

}
