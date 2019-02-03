package model.managers;

import model.businessObjects.IPizza;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrderManager {
    //returns order id
    void createOrder(List<IPizza> pizzas, int userId, LocalDateTime orderCreationTime, String address, String phone);
}
