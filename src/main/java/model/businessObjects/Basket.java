package model.businessObjects;

import java.util.List;

public class Basket implements IBasket {
    private int userId;
    private List<Integer> pizzaIDs;
    private double price;

    @Override
    public void clearBasket() {
        pizzaIDs.clear();
        price = 0;
    }

    @Override
    public void addToBasket(int pizzaId, double pizzaPrice) {
        pizzaIDs.add(pizzaId);
        this.price += pizzaPrice;
    }

    @Override
    public void deleteFromBasket(int pizzaId, double pizzaPrice) {
        pizzaIDs.remove(Integer.valueOf(pizzaId));
        this.price -= pizzaPrice;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int getUserId() {
        return userId;
    }
}
