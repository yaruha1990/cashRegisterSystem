package ua.training.model.dao.impl;

import ua.training.model.dao.CheckDao;
import ua.training.model.entity.Check;
import ua.training.model.entity.Product;
import ua.training.model.services.CheckService;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CheckDaoImpl implements CheckDao {

    private Connection connection;

    public CheckDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int getLatestCheckId() {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("SET @@SESSION.information_schema_stats_expiry = 0");
            ResultSet resultSet = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'cashregister' AND TABLE_NAME = 'check';");
            while (resultSet.next()) return resultSet.getInt("AUTO_INCREMENT");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void createCheck(Check check) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(
        "INSERT INTO `check` (user_id, check_sum, check_date, date_time ) VALUES (?, ?, ?, ?)");
            ps.setInt(1 , check.getUserId());
            ps.setInt(2 ,check.getCheckSum());
            ps.setDate(3, Date.valueOf(check.getDate()));
            ps.setString(4,check.getDateTime());
            ps.execute();

            ps = connection.prepareStatement("insert into `checks_products` (check_id, product_id, product_quantity) values (?,?,?)");
            PreparedStatement preparedStatement = connection.prepareStatement("update product set quantity_in_stock=quantity_in_stock-? where id=?");
            for(Map.Entry<Product, Integer> entry : check.getProducts().entrySet()) {

                Product product = entry.getKey();
                Integer quantity = entry.getValue();
                ps.setInt(1,check.getId());
                ps.setInt(2,product.getId());
                ps.setInt(3,quantity);
                ps.addBatch();

                preparedStatement.setInt(1,quantity);
                preparedStatement.setInt(2,product.getId());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            ps.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
        }
    }

    @Override
    public Check findCheckById(int id) {
        Check check = new Check();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select check_id,user_id,login,product_id,vendor_code,product_name,product_quantity,price," +
                            "check_sum,check_date,date_time from checks_products join `check` on check_id=`check`.id " +
                            "join product on product_id=`product`.id join user on user_id=user.id where check_id=?;");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                check = extractFromResultSet(check,resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public List<Check> findAll() {
        List<Check> checks = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select `check`.id, login, check_sum, date_time from `check` join user on user_id=user.id");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Check check = new Check();
                check.setId(resultSet.getInt("id"));
                check.setUserLogin(resultSet.getString("login"));
                check.setCheckSum(resultSet.getInt("check_sum"));
                check.setDateTime(resultSet.getString("date_time"));
                checks.add(check);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return checks;
    }

    private Check extractFromResultSet(Check check, ResultSet resultSet) throws SQLException {
        Product product = new Product();
        check.setId(resultSet.getInt("check_id"));
        check.setUserId(resultSet.getInt("user_id"));
        check.setUserLogin(resultSet.getString("login"));
        product.setId(resultSet.getInt("product_id"));
        product.setVendorCode(resultSet.getString("vendor_code"));
        product.setProductName(resultSet.getString("product_name"));
        product.setPrice(resultSet.getInt("price"));
        check.getProducts().put(product,resultSet.getInt("product_quantity"));
        check.setCheckSum(resultSet.getInt("check_sum"));
        check.setDate(resultSet.getDate("check_date").toLocalDate());
        check.setDateTime(resultSet.getString("date_time"));
        return check;
    }

    @Override
    public void updateCheck(Check check) {

    }

    @Override
    public void deleteCheck(int id) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("delete from `check` where id=?");
            ps.setInt(1 , id);
            ps.execute();

            ps = connection.prepareStatement("select product_id, product_quantity from checks_products where check_id=?;");
            ps.setInt(1,id);

            PreparedStatement preparedStatement = connection.prepareStatement("update product set quantity_in_stock=quantity_in_stock+? where id=?");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                int productId = resultSet.getInt("product_id");
                int productQuantity = resultSet.getInt("product_quantity");
                preparedStatement.setInt(1,productQuantity);
                preparedStatement.setInt(2,productId);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();

            ps = connection.prepareStatement("delete from checks_products where check_id=?");
            ps.setInt(1,id);
            ps.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void deleteProductFromCheck(int checkId, int productId) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("select product_quantity from checks_products " +
                    "where check_id=? and product_id=?");
            ps.setInt(1,checkId);
            ps.setInt(2,productId);
            int productQuantity = 0;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                productQuantity = rs.getInt("product_quantity");
            }

            ps = connection.prepareStatement("update product set quantity_in_stock=quantity_in_stock+? where id=?");
            ps.setInt(1,productQuantity);
            ps.setInt(2,productId);
            ps.executeUpdate();

            ps = connection.prepareStatement("delete from checks_products where check_id=? and product_id=?");
            ps.setInt(1,checkId);
            ps.setInt(2,productId);
            ps.execute();

            int productCost = 0;
            ps = connection.prepareStatement("select price from product where id=?");
            ps.setInt(1,productId);
            rs = ps.executeQuery();
            while (rs.next()){
                int price = rs.getInt("price");
                productCost = productQuantity*price;
            }

            ps = connection.prepareStatement("update `check` set check_sum=check_sum-? where id=?");
            ps.setInt(1,productCost);
            ps.setInt(2,checkId);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
        }

    }

    @Override
    public void close() throws Exception {

    }
}
