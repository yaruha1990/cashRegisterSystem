package mockito;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.training.controller.command.cashier.AddProductToCheck;
import ua.training.model.entity.Check;
import ua.training.model.entity.Product;
import ua.training.model.services.CheckService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class CheckCreationTest {

    private Connection connection;

    @Test
    public void sessionContainsCheckTest() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        Product product = new Product("20200001","cucmber",10,5);
        Check check = new Check();
        check.getProducts().put(product,5);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("check")).thenReturn(check);
        when(session.getAttribute("btnvalue")).thenReturn("en");
        when(request.getParameter("vendorCode")).thenReturn("10100001");
        when(request.getParameter("quantity")).thenReturn("7");

        AddProductToCheck add = new AddProductToCheck();
        add.execute(request,response);

        int checkSum = new CheckService().calculateCheckSum(check);
        assertTrue(checkSum == 120);
    }

    @Before
    public void getConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashregister?useSSL=false","root","1234");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @After
    public void closeConnection(){
        try {
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
