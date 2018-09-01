package ua.training.model.dao;

import ua.training.model.entity.User;

import java.util.List;

public interface UserDao {
    void createUser(User user);
    User findUserById(int id);
    User findUserByLogin(String login);
    List<User> findAll();
    void updateUser(User user);
    void deleteUser(int id);
}
