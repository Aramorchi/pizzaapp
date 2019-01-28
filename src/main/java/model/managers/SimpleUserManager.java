package model.managers;

import model.businessObjects.IUser;
import model.dao.DBUserDao;
import model.dao.IUserDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SimpleUserManager implements IUserManager{
    private IUserDao userDao;
    private List<IUser> registeredUsers;
    private IUser currentUser;

    public SimpleUserManager() throws IllegalAccessException, SQLException, InstantiationException {
        userDao = new DBUserDao();
        registeredUsers = new ArrayList<>();
    }

    @Override
    public long getCurrentUserId() {
        return currentUser.getUserId();
    }

    @Override
    public void logIn(String login, String password) {
        currentUser = registeredUsers.stream().filter(user -> user.getUserLogin().equals(login) && user.getUserPassword().equals(password))
                .findFirst().get();
    }

    @Override
    public void logOut() {
        currentUser = null;
    }
}
