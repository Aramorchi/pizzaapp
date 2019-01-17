package model.businessObjects;

import model.utils.Status;

import java.time.LocalDateTime;

public interface IOrder {
    void setStatus(Status status);
    void setDeliveredTime(LocalDateTime deliveredTime);
    void addToOrder(IPizza pizza);
    void removeFromOrder(IPizza pizza);
    void setAddress(String address);
    void setPhone(String phone);
    String getPhone();
    String getAddress();
    int getOrderPrice();
    long getOrderId();
    Status getStatus();
}
