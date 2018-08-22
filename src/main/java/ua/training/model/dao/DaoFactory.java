package ua.training.model.dao;

import ua.training.model.dao.impl.DaoFactoryImpl;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao getUserDao();

    public abstract ProductDao getProductDao();

    public abstract CheckDao getCheckDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            daoFactory = new DaoFactoryImpl();
        }
        return daoFactory;
    }
}
