package model.dao;

import model.businessObjects.IBasket;

public interface IBasketDao {
    IBasket getBasketOfUser(long userId);
    void saveBasketOfUserToStorage(IBasket userBasket);
    void deleteUserBasket(long userId);
    void addToBasket(long userId, int pizzaId, double pizzaPrice);
    void removeFromBasket(long userId, int pizzaId);
}
