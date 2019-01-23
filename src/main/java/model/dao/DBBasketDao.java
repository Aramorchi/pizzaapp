package model.dao;

import model.businessObjects.IBasket;

public class DBBasketDao implements IBasketDao {
    @Override
    public IBasket getBasketOfUser(long userId) {
        return null;
    }

    @Override
    public void saveBasketOfUserToStorage(IBasket userBasket) {

    }

    @Override
    public void deleteUserBasket(long userId) {

    }
}
