package model.managers;

import model.businessObjects.IPizza;

public interface IPizzaManager {
    IPizza getPizza(String pizzaName);
}
