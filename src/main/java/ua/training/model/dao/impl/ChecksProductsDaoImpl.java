package ua.training.model.dao.impl;

import ua.training.model.dao.ChecksProductsDao;
import ua.training.model.entity.Check;

import java.sql.Connection;

public class ChecksProductsDaoImpl implements ChecksProductsDao {

    private Connection connection;

    public ChecksProductsDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createCheck(Check check) {

    }

    @Override
    public void close() throws Exception {

    }
}
