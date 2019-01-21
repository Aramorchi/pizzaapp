package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.businessObjects.IUser;
import model.businessObjects.User;

public class DBUserDao implements IUserDao {
    private Connection connection;
    private static final int GETTING_USER_LOGIN_INDEX = 1;
    private static final int GETTING_USER_PASSWORD_INDEX = 2;
    private static final int ADDING_USER_ID_INDEX = 1;
    private static final int ADDING_USER_LOGIN_INDEX = 2;
    private static final int ADDING_USER_PASSWORD_INDEX = 3;
    private static final int ADDING_USER_PHONE_INDEX = 4;
    
    public DBUserDao()
        throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public IUser getUser(String username, String password) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE login=? and pass=?");
            stmt.setString(GETTING_USER_LOGIN_INDEX, username);
            stmt.setString(GETTING_USER_PASSWORD_INDEX, password);
            ResultSet result = stmt.executeQuery();
            
            IUser user = new User();
            result.next();
            user.setUserId(result.getInt("id"));
            user.setUserLogin(result.getString("login"));
            user.setUserPassword(result.getString("pass"));
            user.setUserPhone(result.getString("phone"));
            
            result.close();
            stmt.close();
            
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addUser(IUser user) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO users(id, login, pass, phone) VALUES(?,?,?,?)");
            stmt.setInt(ADDING_USER_ID_INDEX, user.getUserId());
            stmt.setString(ADDING_USER_LOGIN_INDEX, user.getUserLogin());
            stmt.setString(ADDING_USER_PASSWORD_INDEX, user.getUserPassword());
            stmt.setString(ADDING_USER_PHONE_INDEX, user.getUserPhone());
            stmt.executeUpdate();
            
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(String username, String password) {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM users WHERE login=? and pass=?");
            stmt.setString(GETTING_USER_LOGIN_INDEX, username);
            stmt.setString(GETTING_USER_PASSWORD_INDEX, password);
            stmt.execute();
        
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
