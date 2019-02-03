package model.dao;

import model.businessObjects.IPizza;
import model.utils.Size;

import java.util.List;

public interface IPizzaDao {
    IPizza getPizza(String name, Size size);
    IPizza getPizzaById(int pizzaId);
    List<IPizza> getAllPizzas();
    void addPizza(IPizza pizza);
    void deletePizza(String name, Size size);
}
