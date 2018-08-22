package ua.training.model.dao.impl;

import ua.training.model.dao.CheckDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ProductDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactoryImpl extends DaoFactory {

    @Override
    public UserDaoImpl getUserDao(){
        return new UserDaoImpl(getConnection());
    }

    @Override
    public ProductDao getProductDao() {
        return new ProductDaoImpl(getConnection());
    }

    @Override
    public CheckDao getCheckDao() {
        return new CheckDaoImpl(getConnection());
    }

    private Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cashregister", "root", "1234" );
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Connection problems");
        }
    }
}
