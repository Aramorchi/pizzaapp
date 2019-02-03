package model.managers;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import model.businessObjects.IOrder;
import model.businessObjects.IPizza;
import model.businessObjects.Order;
import model.dao.DBOrderDao;
import model.dao.IOrderDao;
import model.utils.Status;

public class SimpleOrderManager implements IOrderManager{
    private int maximumDeliveryMinutes = 50;
    private IOrderDao orderDao;

    public SimpleOrderManager() throws IllegalAccessException, SQLException, InstantiationException {
        orderDao = new DBOrderDao();
    }

    @Override
    public void createOrder(List<IPizza> pizzas, int userId, LocalDateTime orderCreationTime,
        String address, String phone) {
        double price = 0;

        for(IPizza pizza: pizzas) {
            price += pizza.getPrice();
        }

        IOrder newOrder = new Order();
        newOrder.setUserId(userId);
        newOrder.setCreationTime(orderCreationTime);
        newOrder.setDeadline(orderCreationTime.plusMinutes(maximumDeliveryMinutes));
        newOrder.setDeliveredTime(orderCreationTime.plusMinutes(30));
        newOrder.setStatus(Status.ACCEPTED);
        newOrder.setOrderPrice(price);
        newOrder.setAddress(address);
        newOrder.setPhone(phone);
        pizzas.forEach(pizza -> newOrder.addToOrder(pizza.getId()));
        orderDao.addOrder(newOrder);
    }

    public void deliverOrder(long orderId) {
        orderDao.updateOrderStatus(orderId, Status.DELIVERED);
    }

    public List<Long> getOrdersOfUser(int userId) {
        return orderDao.getAllOrdersForUser(userId).stream().map(order -> order.getOrderId()).collect(Collectors.toList());
    }
}
