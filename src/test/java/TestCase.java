import org.junit.*;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.impl.UserDaoImpl;
import ua.training.model.entity.Check;
import ua.training.model.entity.Product;
import ua.training.model.entity.User;
import ua.training.model.services.CheckService;
import ua.training.model.services.PasswordMD5Encoder;
import ua.training.model.services.RegExp;
import ua.training.model.services.UserIdentificationHelper;

import java.sql.*;

public class TestCase {

    private Connection connection;

    @Test
    public void getUserByIdTest() {
        UserDao userDao = new UserDaoImpl(connection);
        User admin = userDao.findUserById(1);
        Assert.assertEquals("admin", admin.getLogin());
    }

    @Test
    public void passEncodingTest(){
        PasswordMD5Encoder encoder = new PasswordMD5Encoder();
        Assert.assertEquals("62B4750DF747D46941BA9136ED86DBC9",encoder.getMD5EncodedPassword("ioanna"));
    }

    @Test
    public void getUserRoleByLoginTest(){
        UserIdentificationHelper helper = new UserIdentificationHelper(new UserDaoImpl(connection));
        Assert.assertEquals("admin",helper.getRoleByLogin("admin"));
    }

    @Test
    public void isUserExistTest(){
        UserIdentificationHelper helper = new UserIdentificationHelper(new UserDaoImpl(connection));
        Assert.assertTrue(helper.isUserExist("admin"));
    }

    @Test
    public void isUserValidTest(){
        UserIdentificationHelper helper = new UserIdentificationHelper(new UserDaoImpl(connection));
        Assert.assertTrue(helper.isUserValid("admin","admin"));
    }

    @Test
    public void lettersDigitsRegExpTest(){
        RegExp regExp = new RegExp();
        Assert.assertTrue(regExp.isValidInput(RegExp.LETTERS_DIGITS_REGEXP,"ЇїжакіІєЄ123"));
    }

    @Test
    public void vendorCodeRegExpTest(){
        RegExp regExp = new RegExp();
        Assert.assertTrue(regExp.isValidInput(RegExp.VENDOR_CODE_REGEXP,"123456789"));
    }

    @Test
    public void calculateCheckSumTest(){
        CheckService checkService = new CheckService();
        Check check = new Check();
        Product product = new Product();
        product.setPrice(10);
        product.setVendorCode("tomato");
        Product product1 = new Product();
        product1.setPrice(20);
        product1.setVendorCode("cucmber");
        checkService.addProductToCheck(check,product,5);
        checkService.addProductToCheck(check,product1,5);
        Assert.assertEquals(150,checkService.calculateCheckSum(check));
    }

    @Test
    public void addProductToCheckTest(){
        CheckService checkService = new CheckService();
        Check check = new Check();
        Product product = new Product();
        product.setVendorCode("tomato");
        Product product1 = new Product();
        product1.setVendorCode("cucmber");
        checkService.addProductToCheck(check,product,3);
        checkService.addProductToCheck(check,product1,3);
        Assert.assertEquals(2,check.getProducts().size());
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
