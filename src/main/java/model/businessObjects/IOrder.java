package model.businessObjects;

import model.utils.Status;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrder {
    void setStatus(Status status);
    void setCreationTime(LocalDateTime creationTime);
    void setDeliveredTime(LocalDateTime deliveredTime);
    void setDeadline(LocalDateTime deadline);
    LocalDateTime getCreationTime();
    LocalDateTime getDeliveredTime();
    LocalDateTime getDeadline();
    void addToOrder(int pizzaId);
    void removeFromOrder(int pizzaId);
    List<Integer> getPizzasIDs();
    void setAddress(String address);
    void setPhone(String phone);
    String getPhone();
    String getAddress();
    void setOrderPrice(int price);
    int getOrderPrice();
    long getOrderId();
    void setOrderId(long orderId);
    int getUserId();
    void setUserId(int userId);
    Status getStatus();
}
