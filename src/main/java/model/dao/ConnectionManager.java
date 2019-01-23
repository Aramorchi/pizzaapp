package model.dao;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Lenovo on 22.01.2019.
 */
public class ConnectionManager {
  private static ConnectionManager connectionManager;
  private Connection connection;
  
  private ConnectionManager()
      throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    setMySQLConnection();
  }
  
  public static ConnectionManager getInstance()
      throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    if(connectionManager == null) {
      connectionManager = new ConnectionManager();
    }
    return connectionManager;
  }
  
  public Connection getConnection() {
    return connection;
  }
  
  private void setMySQLConnection() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException{
    Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
    DriverManager.registerDriver(driver);
  
    String url      = "jdbc:mysql://localhost:3306/pizzaapp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String user     = "root";
    String password = "";
  
    connection = DriverManager.getConnection(url, user, password);
  }
}
