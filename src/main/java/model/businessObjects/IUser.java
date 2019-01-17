package model.businessObjects;

public interface IUser {
    void setUserId(long id);
    long getUserId();
    void setUserPhone(String phone);
    String getUserPhone();
    void setUserPassword(String password);
    String getUserPassword();
}
