package model.dao;

import model.businessObjects.IPizza;
import model.businessObjects.Pizza;
import model.utils.Size;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBPizzaDao implements IPizzaDao {
    private Connection connection;
    private static final int GETTING_PIZZA_NAME_INDEX = 1;
    private static final int GETTING_PIZZA_SIZE_INDEX = 2;

    private static final int ADDING_PIZZA_NAME_INDEX = 1;
    private static final int ADDING_PIZZA_SIZE_INDEX = 2;
    private static final int ADDING_PIZZA_PRICE_INDEX = 3;

    public DBPizzaDao()
            throws SQLException, InstantiationException, IllegalAccessException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public IPizza getPizza(String pizzaName, Size size) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM pizzas WHERE pizzaName=? and pizzaSize=?");
            stmt.setString(GETTING_PIZZA_NAME_INDEX, pizzaName);
            stmt.setString(GETTING_PIZZA_SIZE_INDEX, size.name());
            ResultSet result = stmt.executeQuery();

            IPizza pizza = new Pizza();
            result.next();
            pizza.setId(result.getInt("id"));
            pizza.setName(result.getString("pizzaName"));
            pizza.setPrice(result.getInt("price"));
            pizza.setSize(Size.valueOf(result.getString("pizzaSize")));

            result.close();
            stmt.close();

            return pizza;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addPizza(IPizza pizza) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO pizzas(pizzaName, pizzaSize, price) VALUES(?,?,?)");
            stmt.setString(ADDING_PIZZA_NAME_INDEX, pizza.getName());
            stmt.setString(ADDING_PIZZA_SIZE_INDEX, pizza.getSize().name());
            stmt.setInt(ADDING_PIZZA_PRICE_INDEX, pizza.getPrice());
            stmt.executeUpdate();

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePizza(String username, Size size) {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM pizzas WHERE pizzaName=? and pizzaSize=?");
            stmt.setString(GETTING_PIZZA_NAME_INDEX, username);
            stmt.setString(GETTING_PIZZA_SIZE_INDEX, size.name());
            stmt.execute();

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
