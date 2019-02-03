package model.dao;

import model.businessObjects.IOrder;
import model.utils.Status;

import java.util.List;

public interface IOrderDao {
    List<IOrder> getAllOrdersForUser(long userId);
    IOrder getOrder(long orderId);
    void addOrder(IOrder order);
    void deleteOrder(long orderId);
    void updateOrderStatus(long orderId, Status status);
    Long getLastOrderId();
}
