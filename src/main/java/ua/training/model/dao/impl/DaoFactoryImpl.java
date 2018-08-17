package ua.training.model.dao.impl;

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

    private Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cashregister?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "1234" );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
