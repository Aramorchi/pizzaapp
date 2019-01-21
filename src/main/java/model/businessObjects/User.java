package model.businessObjects;

public class User implements IUser{
  private int id;
  private String login;
  private String password;
  private String phone;
  
  
  @Override
  public void setUserId(int id) {
    this.id = id;
  }
  
  @Override
  public int getUserId() {
    return id;
  }
  
  @Override
  public void setUserPhone(String phone) {
    this.phone = phone;
  }
  
  @Override
  public void setUserLogin(String login) {
    this.login = login;
  }
  
  @Override
  public String getUserLogin() {
    return login;
  }
  
  @Override
  public String getUserPhone() {
    return phone;
  }
  
  @Override
  public void setUserPassword(String password) {
    this.password = password;
  }
  
  @Override
  public String getUserPassword() {
    return password;
  }
}
