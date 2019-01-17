package model.managers;

public interface IUserManager {
    long getCurrentUserId();
    void logIn(String login, String password);
    void logOut();
}
