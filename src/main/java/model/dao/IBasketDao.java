package model.dao;

import model.businessObjects.IBasket;

public interface IBasketDao {
    IBasket getBasketOfUser(long userId);
    void saveBasketOfUserToStorage(IBasket userBasket);
    void deleteUserBasket(long userId);
}
