package model.managers;

import model.utils.Size;

import java.util.List;

public interface IBasketManager {
    void addPizzaToBasket(int userId, String pizzaName, Size size);
    void deletePizzaFromBasket(int userId, String pizzaName, Size size);
    void createOrder(int userId, String userAddress, String userPhone);
    List<String> getBasketItems(int userId);
}
