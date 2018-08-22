package ua.training.model.dao.impl;

import ua.training.model.dao.CheckDao;
import ua.training.model.entity.Check;
import ua.training.model.entity.Product;

import java.sql.*;
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
            ResultSet resultSet = statement.executeQuery("select max(id) from `check`");
            while (resultSet.next()) return resultSet.getInt("max(id)");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void createCheck(Check check) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(
        "INSERT INTO `check` (user_id , check_sum, date, date_time ) VALUES (?, ?, ?, ?)");
            ps.setInt(1 , check.getUserId());
            ps.setDouble(2 ,check.getCheckSum());
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
        return null;
    }

    @Override
    public List<Check> findAll() {
        return null;
    }

    @Override
    public void updateCheck(Check check) {

    }

    @Override
    public void deleteCheck(int id) {

    }

    @Override
    public void close() throws Exception {

    }
}
