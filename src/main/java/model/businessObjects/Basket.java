package model.businessObjects;

import java.util.HashMap;
import java.util.Map;

public class Basket implements IBasket {
    private int userId;
    private Map<Integer, Double> pizzaIDs;
    private double price;

    public Basket() {
        pizzaIDs = new HashMap<>();
    }

    @Override
    public void clearBasket() {
        pizzaIDs.clear();
        price = 0;
    }

    @Override
    public void addToBasket(int pizzaId, double pizzaPrice) {
        pizzaIDs.put(pizzaId, pizzaPrice);
        this.price += pizzaPrice;
    }

    @Override
    public void deleteFromBasket(int pizzaId, double pizzaPrice) {
        pizzaIDs.remove(pizzaId);
        this.price -= pizzaPrice;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getPizzaPrice(int pizzaId) {
        return pizzaIDs.get(pizzaId);
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
