package model.dao;

import model.businessObjects.IPizza;

public interface IPizzaDao {
    IPizza getPizza(String name);
    void addPizza(IPizza pizza);
    void deletePizza(String name);
}
