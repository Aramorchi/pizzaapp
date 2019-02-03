package model.businessObjects;

import java.util.Map;

public interface IBasket {
    void clearBasket();
    void addToBasket(int pizzaId, double pizzaPrice);
    void deleteFromBasket(int pizzaId, double pizzaPrice);
    double getPrice();
    double getPizzaPrice(int pizzaId);
    Map<Integer, Double> getBasketContent();
    void setUserId(int userId);
    int getUserId();
}
