package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    private static final int ADDING_USER_PHONE_PHONE_INDEX = 1;
    private static final int ADDING_USER_PHONE_USER_ID_INDEX = 2;
    
    public DBUserDao()
        throws SQLException, InstantiationException, IllegalAccessException {
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
    public List<IUser> getAllUsers() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users ");
            ResultSet result = stmt.executeQuery();

            List<IUser> users = new ArrayList<>();

            while(result.next()) {
                IUser user = new User();
                user.setUserId(result.getInt("id"));
                user.setUserLogin(result.getString("login"));
                user.setUserPassword(result.getString("pass"));
                user.setUserPhone(result.getString("phone"));

                users.add(user);
            }


            result.close();
            stmt.close();

            return users;
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
    public void addUserPhone(int userId, String phone) {
        try {
            PreparedStatement stmt = connection.prepareStatement("UPDATE users SET phone=? WHERE id=?");
            stmt.setString(ADDING_USER_PHONE_PHONE_INDEX, phone);
            stmt.setInt(ADDING_USER_PHONE_USER_ID_INDEX, userId);
            stmt.execute();

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

    public static void main(String[] args) throws IllegalAccessException, SQLException, InstantiationException {
        DBUserDao dbUserDao = new DBUserDao();

        IUser user = new User();
        user.setUserLogin("fafnir");
        user.setUserPassword("LemanRuss");

        dbUserDao.addUser(user);

        user = dbUserDao.getUser("fafnir", "LemanRuss");

        dbUserDao.addUserPhone(user.getUserId(), "555 7272727");

        System.out.println(dbUserDao.getUser("fafnir", "LemanRuss").getUserPhone());
    }
}
