package ua.training.model.dao.impl;

import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.services.PasswordMD5Encoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private Connection connection;
    private PasswordMD5Encoder passwordMD5Encoder = new PasswordMD5Encoder();

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createUser(User user) {
        try (PreparedStatement ps = connection.prepareStatement
                ("INSERT INTO user (login , password, role ) VALUES (?, ?, ? )")){
            ps.setString(1 , user.getLogin());
            ps.setString(2 ,passwordMD5Encoder.getMD5EncodedPassword(user.getPassword()));
            ps.setString(3,user.getRole());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Such login is already taken");
        }
    }

    @Override
    public User findUserById(int id) {
        User user = null;
        try (PreparedStatement ps = connection.prepareStatement("select * from user where id = ?")){
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                user = extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findUserByLogin(String login) {
        User user = null;
        try (PreparedStatement ps = connection.prepareStatement("select * from user where login = ?")){
            ps.setString(1,login);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                user = extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement("select * from user")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                User user = extractFromResultSet(resultSet);
                userList.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }

    private User extractFromResultSet(ResultSet resultSet)
            throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(resultSet.getString("role"));
        return user;
    }

    @Override
    public void updateUser(User user) {
        try(PreparedStatement ps = connection.prepareStatement("update user set login=?,password=?,role=? where " +
                "id=?")) {
            ps.setString(1,user.getLogin());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getRole());
            ps.setInt(4,user.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Such login is already taken");
        }
    }

    @Override
    public void deleteUser(int id) {
        try(PreparedStatement ps = connection.prepareStatement("delete from user where id=?")) {
            ps.setInt(1,id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
