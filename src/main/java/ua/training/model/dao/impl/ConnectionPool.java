package ua.training.model.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ConnectionPool {

    private ConnectionPool(){}
    private static Set<Connection> openedConnections = new HashSet<>();
    private static ConnectionPool connectionPool;

    public static ConnectionPool getInstance(){
        if (connectionPool == null){
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }

    public Connection getConnection(){
        if (openedConnections.size() > 30){
            throw new RuntimeException("Too many unclosed connections");
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashregister?useSSL=false","root","1234");
        }catch (SQLException e){
            e.printStackTrace();
        }
        openedConnections.add(connection);
        return connection;
    }

    public void closeConnection(Connection connection){
        try {
            connection.close();
            openedConnections.remove(connection);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
