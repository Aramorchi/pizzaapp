package model.businessObjects;

import model.utils.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Order implements IOrder {
    private long orderId;
    private int userId;
    private Status status;
    private List<Integer> pizzasIDs;
    private String phone;
    private String address;
    private LocalDateTime creationTime;
    private LocalDateTime deadline;
    private LocalDateTime deliveredTime;
    private int price;


    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public void setDeliveredTime(LocalDateTime deliveredTime) {
        this.deliveredTime = deliveredTime;
    }

    @Override
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    @Override
    public LocalDateTime getDeliveredTime() {
        return deliveredTime;
    }

    @Override
    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public void addToOrder(int pizzaId) {
        if(pizzasIDs == null) {
            pizzasIDs = new ArrayList<>();
        }
        pizzasIDs.add(pizzaId);
    }

    @Override
    public void removeFromOrder(int pizzaId) {
        Logger logger = Logger.getGlobal();
        if(pizzasIDs == null) {
            logger.info("Order is empty!");
        } else if (!pizzasIDs.contains(pizzaId)) {
            logger.info(String.format("Order doesn't contain '%s' pizza", pizzaId));
        } else {
            pizzasIDs.remove(pizzaId);
        }
    }

    @Override
    public List<Integer> getPizzasIDs() {
        return pizzasIDs;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setOrderPrice(int price) {
        this.price = price;
    }

    @Override
    public int getOrderPrice() {
        return price;
    }

    @Override
    public long getOrderId() {
        return orderId;
    }

    @Override
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public Status getStatus() {
        return  status;
    }
}
