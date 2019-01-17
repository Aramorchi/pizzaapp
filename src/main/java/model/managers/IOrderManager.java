package model.managers;

import model.businessObjects.IPizza;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrderManager {
    //returns order id
    long createOrder(List<IPizza> pizzas, long userId, LocalDateTime orderCreationTime, String address, String phone);
}
