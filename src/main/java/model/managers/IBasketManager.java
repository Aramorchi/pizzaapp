package model.managers;

import model.utils.Size;

public interface IBasketManager {
    void addPizzaToBasket(long userId, String pizzaName, Size size);
    void deletePizzaFromBasket(long userId, String pizzaName, Size size);

    //returns orderId
    long createOrder(long userId, String userAddress, String userPhone);
}
