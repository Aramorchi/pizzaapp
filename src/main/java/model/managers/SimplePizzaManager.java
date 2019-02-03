package model.managers;

import model.businessObjects.IPizza;
import model.businessObjects.Pizza;
import model.dao.DBPizzaDao;
import model.dao.IPizzaDao;
import model.utils.Size;

import java.sql.SQLException;
import java.util.List;

public class SimplePizzaManager implements IPizzaManager{
    private IPizzaDao pizzaDao;

    public SimplePizzaManager() throws IllegalAccessException, SQLException, InstantiationException {
        pizzaDao = new DBPizzaDao();
    }

    @Override
    public IPizza getPizza(String pizzaName, Size size) {
        return pizzaDao.getPizza(pizzaName, size);
    }

    @Override
    public IPizza getPizzaById(int pizzaId) {
        return pizzaDao.getPizzaById(pizzaId);
    }

    @Override
    public List<IPizza> getAllPizzas() {
        return pizzaDao.getAllPizzas();
    }

    @Override
    public void addNewPizza(String pizzaName, Size size, double price) {
        IPizza newPizza = new Pizza(pizzaName, size, price);
        pizzaDao.addPizza(newPizza);
    }

    @Override
    public void deletePizza(String pizzaName, Size size) {
        pizzaDao.deletePizza(pizzaName, size);
    }

    @Override
    public void changePizzaPrice(String pizzaName, Size size, double price) {

    }
}
