package model.dao;

import model.businessObjects.Basket;
import model.businessObjects.IBasket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBBasketDao implements IBasketDao {
    private Connection connection;
    private static final int GETTING_BASKET_USER_ID_INDEX = 1;

    private static final int GETTING_ALL_USER_ORDERS_USER_ID_INDEX = 1;

    private static final int DELETING_ORDER_ORDER_ID_INDEX = 1;

    private static final int ADDING_ORDER_USER_ID_INDEX = 1;
    private static final int ADDING_ORDER_ORDER_ID_INDEX = 2;
    private static final int ADDING_ORDER_PIZZA_ID_INDEX = 3;
    private static final int ADDING_ORDER_PRICE_INDEX = 4;
    private static final int ADDING_ORDER_CREATION_TIME_INDEX = 5;
    private static final int ADDING_ORDER_DEADLINE_INDEX = 6;
    private static final int ADDING_ORDER_DELIVERED_TIME_INDEX = 7;
    private static final int ADDING_ORDER_ORDER_STATUS_INDEX = 8;
    private static final int ADDING_ORDER_ADDRESS_INDEX = 9;
    private static final int ADDING_ORDER_PHONE_INDEX = 10;

    public DBBasketDao()
            throws SQLException, InstantiationException, IllegalAccessException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public IBasket getBasketOfUser(long userId) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM basket WHERE userId=?");
            stmt.setLong(GETTING_BASKET_USER_ID_INDEX, userId);
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
    public void saveBasketOfUserToStorage(IBasket userBasket) {

    }

    @Override
    public void deleteUserBasket(long userId) {

    }

    @Override
    public void addToBasket(long userId, int pizzaId, double pizzaPrice) {

    }

    @Override
    public void removeFromBasket(long userId, int pizzaId) {

    }
}
