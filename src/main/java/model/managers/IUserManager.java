package model.managers;

public interface IUserManager {
    int getCurrentUserId();
    String getCurrentUserPhone();
    void logIn(String login, String password);
    void logOut();
    void register(String login, String password);
    void register(String login, String password, String phone);
    void addPhoneNumber(String phone);
}
