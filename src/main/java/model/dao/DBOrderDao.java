package model.dao;

import model.businessObjects.*;
import model.utils.Size;
import model.utils.Status;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DBOrderDao implements IOrderDao {
    private Connection connection;
    private static final int GETTING_ORDER_ORDER_ID_INDEX = 1;

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

    public DBOrderDao()
            throws SQLException, InstantiationException, IllegalAccessException {
        try {
            connection = ConnectionManager.getInstance().getConnection();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public IOrder getOrder(long orderId) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM orders WHERE orderId=?");
            stmt.setLong(GETTING_ORDER_ORDER_ID_INDEX, orderId);
            ResultSet result = stmt.executeQuery();

            IOrder order = new Order();
            result.next();
            order.setOrderId(result.getLong("orderId"));
            order.setUserId(result.getInt("userId"));
            order.setOrderPrice(result.getInt("price"));
            order.setCreationTime(result.getTimestamp("momentOfOrder").toLocalDateTime());
            order.setDeadline(result.getTimestamp("deadline").toLocalDateTime());
            order.setDeliveredTime(result.getTimestamp("deliveredTime").toLocalDateTime());
            order.setStatus(Status.valueOf(result.getString("orderStatus")));
            order.setAddress(result.getString("address"));
            order.setPhone(result.getString("phone"));
            order.addToOrder(result.getInt("pizzaId"));
            while(result.next()) {
                order.addToOrder(result.getInt("pizzaId"));
            }

            result.close();
            stmt.close();

            return order;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addOrder(IOrder order) {
        int userId = order.getUserId();
        long orderId = order.getOrderId();
        int price = order.getOrderPrice();
        LocalDateTime creationTime = order.getCreationTime();
        LocalDateTime deadline = order.getDeadline();
        LocalDateTime deliveredTime = order.getDeliveredTime();
        Status orderStatus = order.getStatus();
        String address = order.getAddress();
        String phone = order.getPhone();

        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO orders VALUES(?,?,?,?,?,?,?,?,?,?)");
                order.getPizzasIDs().stream().forEach(pizzaId -> {

                            try {
                                stmt.setInt(ADDING_ORDER_USER_ID_INDEX, userId);
                                stmt.setLong(ADDING_ORDER_ORDER_ID_INDEX, orderId);
                                stmt.setInt(ADDING_ORDER_PIZZA_ID_INDEX, pizzaId);
                                stmt.setInt(ADDING_ORDER_PRICE_INDEX, price);
                                stmt.setTimestamp(ADDING_ORDER_CREATION_TIME_INDEX, Timestamp.valueOf(creationTime));
                                stmt.setTimestamp(ADDING_ORDER_DEADLINE_INDEX, Timestamp.valueOf(deadline));
                                stmt.setTimestamp(ADDING_ORDER_DELIVERED_TIME_INDEX, Timestamp.valueOf(deliveredTime));
                                stmt.setString(ADDING_ORDER_ORDER_STATUS_INDEX, orderStatus.name());
                                stmt.setString(ADDING_ORDER_ADDRESS_INDEX, address);
                                stmt.setString(ADDING_ORDER_PHONE_INDEX, phone);
                                stmt.executeUpdate();
                            } catch (SQLException e) {
                                Logger.getGlobal().info(e.getMessage());
                            }
                        }
                );

                stmt.close();
        }  catch (SQLException e) {
        e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(long orderId) {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM orders WHERE orderId=?");
            stmt.setLong(DELETING_ORDER_ORDER_ID_INDEX, orderId);
            stmt.execute();

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<IOrder> getAllOrdersForUser(long userId) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT DISTINCT orderId FROM orders WHERE userId=?");
            stmt.setLong(GETTING_ALL_USER_ORDERS_USER_ID_INDEX, userId);
            ResultSet result = stmt.executeQuery();

            List<Integer> ordersIDs = new ArrayList<>();

            while(result.next()) {
                ordersIDs.add(result.getInt("orderId"));
            }
            result.close();
            stmt.close();

            return ordersIDs.stream().map(this::getOrder).collect(Collectors.toList());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        IUser user = new User();

        user.setUserLogin("Ricardo Milos");
        user.setUserPassword("FlexTime");

        IPizza pizzaOne = new Pizza();
        IPizza pizzaTwo = new Pizza();
        IPizza pizzaThree = new Pizza();

        pizzaOne.setId(4);
        pizzaTwo.setId(6);
        pizzaThree.setId(7);

        pizzaOne.setName("Flugegehaimen");
        pizzaTwo.setName("Ricardo Milos");
        pizzaThree.setName("Janitor");

        pizzaOne.setPrice(62);
        pizzaTwo.setPrice(49);
        pizzaThree.setPrice(90);

        pizzaOne.setSize(Size.MEDIUM);
        pizzaTwo.setSize(Size.SMALL);
        pizzaThree.setSize(Size.LARGE);

        IOrder order = new Order();
        IOrder secondOrder = new Order();

        order.setUserId(1);
        order.addToOrder(1);
        order.addToOrder(3);
        order.setStatus(Status.BAKING);
        order.setPhone("11111111");
        order.setAddress("Bratislava, Communism street");
        order.setOrderPrice(126);
        order.setCreationTime(LocalDateTime.now());
        order.setDeadline(LocalDateTime.now().plusMinutes(50));
        order.setDeliveredTime(LocalDateTime.now().plusMinutes(42));

        secondOrder.setUserId(1);
        secondOrder.addToOrder(2);
        secondOrder.addToOrder(2);
        secondOrder.addToOrder(2);
        secondOrder.setStatus(Status.BAKING);
        secondOrder.setPhone("11112111");
        secondOrder.setAddress("Bratislava, Czech street");
        secondOrder.setOrderPrice(147);
        secondOrder.setCreationTime(LocalDateTime.now());
        secondOrder.setDeadline(LocalDateTime.now().plusMinutes(50));
        secondOrder.setDeliveredTime(LocalDateTime.now().plusMinutes(38));

        try {
            IUserDao dbUserDao = new DBUserDao();
            IOrderDao dbOrderDao = new DBOrderDao();
            IPizzaDao dbPizzaDao = new DBPizzaDao();

            dbUserDao.addUser(user);

            dbPizzaDao.addPizza(pizzaOne);
            dbPizzaDao.addPizza(pizzaTwo);
            dbPizzaDao.addPizza(pizzaThree);

            dbOrderDao.addOrder(order);
            dbOrderDao.addOrder(secondOrder);

            List<IOrder> orders = dbOrderDao.getAllOrdersForUser(1);
            orders.stream().forEach(orderr -> System.out.println(orderr.getAddress()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
