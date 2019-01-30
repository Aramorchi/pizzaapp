package model.dao;

import model.businessObjects.IUser;

import java.util.List;

public interface IUserDao {
    IUser getUser(String username, String password);
    List<IUser> getAllUsers();
    void addUser(IUser user);
    void addUserPhone(int userId, String phone);
    void deleteUser(String username, String password);
}
