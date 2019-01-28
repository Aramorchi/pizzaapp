package model.dao;

import model.businessObjects.IBasket;

public interface IBasketDao {
    IBasket getBasketOfUser(int userId);
    void deleteUserBasket(int userId);
    void addToBasket(int userId, int pizzaId, double pizzaPrice);
    void removeFromBasket(int userId, int pizzaId);
}
