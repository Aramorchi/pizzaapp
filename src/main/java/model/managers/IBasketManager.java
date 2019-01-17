package model.managers;

import model.businessObjects.IPizza;

public interface IBasketManager {
    void addPizzaToBasket(long userId, IPizza pizza);

    //returns orderId
    long createOrder(long userId);
}
