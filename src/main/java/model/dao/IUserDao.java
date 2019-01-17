package model.dao;

import model.businessObjects.IUser;

public interface IUserDao {
    IUser getUser(String username, String password);
    void addUser(IUser user);
    void deleteUser(String username, String password);
}
