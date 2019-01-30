package model.managers;

import model.utils.Size;

public interface IBasketManager {
    void addPizzaToBasket(int userId, String pizzaName, Size size);
    void deletePizzaFromBasket(int userId, String pizzaName, Size size);

    //returns orderId
    long createOrder(int userId, String userAddress, String userPhone);
}
