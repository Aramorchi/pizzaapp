package model.managers;

import model.businessObjects.IUser;
import model.businessObjects.User;
import model.dao.DBUserDao;
import model.dao.IUserDao;

import java.sql.SQLException;

public class SimpleUserManager implements IUserManager{
    private IUserDao userDao;
    private IUser currentUser;

    public SimpleUserManager() throws IllegalAccessException, SQLException, InstantiationException {
        userDao = new DBUserDao();
    }

    @Override
    public int getCurrentUserId() {
        return currentUser.getUserId();
    }

    @Override
    public String getCurrentUserPhone() {
        return currentUser.getUserPhone();
    }

    @Override
    public void logIn(String login, String password) {
        currentUser = userDao.getAllUsers().stream().filter(user -> user.getUserLogin().equals(login) && user.getUserPassword().equals(password))
                .findFirst().get();
    }

    @Override
    public void logOut() {
        currentUser = null;
    }

    @Override
    public void register(String login, String password) {
        IUser newUser = new User();
        newUser.setUserLogin(login);
        newUser.setUserPassword(password);
        userDao.addUser(newUser);
    }

    @Override
    public void register(String login, String password, String phone) {
        IUser newUser = new User();
        newUser.setUserLogin(login);
        newUser.setUserPassword(password);
        newUser.setUserPhone(phone);
        userDao.addUser(newUser);
    }

    @Override
    public void addPhoneNumber(String phone) {
        currentUser.setUserPhone(phone);
        userDao.addUserPhone(currentUser.getUserId(), phone);
    }
}
