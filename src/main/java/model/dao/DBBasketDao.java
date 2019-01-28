package model.dao;

import model.businessObjects.Basket;
import model.businessObjects.IBasket;

import java.sql.*;

public class DBBasketDao implements IBasketDao {
    private Connection connection;
    private static final int GETTING_BASKET_USER_ID_INDEX = 1;

    private static final int DELETING_BASKET_ORDER_ID_INDEX = 1;

    private static final int REMOVING_FROM_BASKET_USER_ID_INDEX = 1;
    private static final int REMOVING_FROM_BASKET_PIZZA_ID = 2;

    private static final int ADDING_BASKET_USER_ID_INDEX = 1;
    private static final int ADDING_BASKET_PIZZA_ID_INDEX = 2;
    private static final int ADDING_BASKET_PIZZA_PRICE_INDEX = 3;

    public DBBasketDao()
            throws SQLException, InstantiationException, IllegalAccessException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public IBasket getBasketOfUser(int userId) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM basket WHERE userId=?");
            stmt.setInt(GETTING_BASKET_USER_ID_INDEX, userId);
            ResultSet result = stmt.executeQuery();

            IBasket basket = new Basket();
            result.next();
            basket.setUserId(result.getInt("userId"));
            basket.addToBasket(result.getInt("pizzaId"), result.getDouble("pizzaPrice"));
            while(result.next()) {
                basket.addToBasket(result.getInt("pizzaId"), result.getDouble("pizzaPrice"));
            }

            result.close();
            stmt.close();

            return basket;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void deleteUserBasket(int userId) {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM basket WHERE userId=?");
            stmt.setInt(DELETING_BASKET_ORDER_ID_INDEX, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addToBasket(int userId, int pizzaId, double pizzaPrice) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO basket VALUES(?,?,?)");
            stmt.setInt(ADDING_BASKET_USER_ID_INDEX, userId);
            stmt.setInt(ADDING_BASKET_PIZZA_ID_INDEX, pizzaId);
            stmt.setDouble(ADDING_BASKET_PIZZA_PRICE_INDEX, pizzaPrice);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeFromBasket(int userId, int pizzaId) {
        try {
            PreparedStatement stmt = connection.prepareStatement("REMOVE FROM basket WHERE userId=? AND pizzaId=?");
            stmt.setInt(REMOVING_FROM_BASKET_USER_ID_INDEX, userId);
            stmt.setInt(REMOVING_FROM_BASKET_PIZZA_ID, pizzaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
