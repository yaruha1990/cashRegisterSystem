package ua.training.model.services;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;

import java.util.List;

public class UserIdentificationHelper {

    private UserDao userDao;

    public UserIdentificationHelper(){
        userDao = DaoFactory.getInstance().getUserDao();
    }

    /**
     * Check existence of user in system depending on the user's login which is unique
     * @param login
     * @return boolean does user exist or not
     */
    public boolean isUserExist(String login){
        List<User> users = userDao.findAll();
        for (User user:users) {
            if (user.getLogin().equals(login)){
                return true;
            }
        }
        return false;
    }

    /**
     * Check user's identity depending on the login and password
     * @param login
     * @param password
     * @return boolean is user valid or not
     */
    public boolean isUserValid(String login, String password){
        List<User> users = userDao.findAll();
        for (User user:users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(new PasswordMD5Encoder().getMD5EncodedPassword(password))){
                return true;
            }
        }
        return false;
    }

    /**
     * Get user's role depending it's login
     * @param login
     * @return user's role
     */
    public String getRoleByLogin(String login){
        return userDao.findUserByLogin(login).getRole();
    }
}
