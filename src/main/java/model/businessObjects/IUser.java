package model.businessObjects;

public interface IUser {
    void setUserId(int id);
    int getUserId();
    void setUserPhone(String phone);
    void setUserLogin(String login);
    String getUserLogin();
    String getUserPhone();
    void setUserPassword(String password);
    String getUserPassword();
}
