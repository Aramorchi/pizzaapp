package model.managers;

import model.businessObjects.IPizza;
import model.utils.Size;

public interface IPizzaManager {
    IPizza getPizza(String pizzaName, Size size);
}
