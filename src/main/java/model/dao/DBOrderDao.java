package model.dao;

import model.businessObjects.IOrder;

import java.util.List;

public class DBOrderDao implements IOrderDao {
    @Override
    public List<IOrder> getAllOrdersForUser(long userId) {
        return null;
    }

    @Override
    public IOrder getOrder(long orderId) {
        return null;
    }

    @Override
    public void addOrder(IOrder order) {

    }

    @Override
    public void deleteOrder(long orderId) {

    }
}
