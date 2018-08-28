package ua.training.model.dao.impl;

import ua.training.model.dao.CheckDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ProductDao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactoryImpl extends DaoFactory {

    private Connection connection;

    public DaoFactoryImpl() {
        this.connection = getConnection();
    }

    @Override
    public UserDaoImpl getUserDao(){
        return new UserDaoImpl(connection);
    }

    @Override
    public ProductDao getProductDao() {
        return new ProductDaoImpl(connection);
    }

    @Override
    public CheckDao getCheckDao() {
        return new CheckDaoImpl(connection);
    }

    private Connection getConnection(){
        try {
            Context initContext= new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/TestDB");
            return ds.getConnection();
        }catch (NamingException | SQLException e){
            e.printStackTrace();
            throw new RuntimeException("DB connection problems");
        }
    }
}
