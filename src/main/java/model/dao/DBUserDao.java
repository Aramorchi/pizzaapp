package model.dao;

import model.businessObjects.IUser;

public class DBUserDao implements IUserDao {
    @Override
    public IUser getUser(String username, String password) {
        return null;
    }

    @Override
    public void addUser(IUser user) {

    }

    @Override
    public void deleteUser(String username, String password) {

    }
}
