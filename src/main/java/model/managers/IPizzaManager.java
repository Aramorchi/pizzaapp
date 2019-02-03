package model.managers;

import model.businessObjects.IPizza;
import model.utils.Size;

import java.util.List;

public interface IPizzaManager {
    IPizza getPizza(String pizzaName, Size size);
    IPizza getPizzaById(int pizzaId);
    List<IPizza> getAllPizzas();
    void addNewPizza(String pizzaName, Size size, double price);
    void deletePizza(String pizzaName, Size size);
    void changePizzaPrice(String pizzaName, Size size, double price);
}
