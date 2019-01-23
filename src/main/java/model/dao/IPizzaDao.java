package model.dao;

import model.businessObjects.IPizza;
import model.utils.Size;

public interface IPizzaDao {
    IPizza getPizza(String name, Size size);
    void addPizza(IPizza pizza);
    void deletePizza(String name, Size size);
}
