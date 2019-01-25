package model.businessObjects;

public interface IBasket {
    void clearBasket();
    void addToBasket(int pizzaId, double pizzaPrice);
    void deleteFromBasket(int pizzaId, double pizzaPrice);
    double getPrice();
    void setUserId(int userId);
    int getUserId();
}
