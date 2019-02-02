package model.managers;

import model.businessObjects.IPizza;
import model.dao.DBBasketDao;
import model.utils.Size;

import java.sql.SQLException;

public class SimpleBasketManager implements IBasketManager{
    private DBBasketDao basketDao;
    private IPizzaManager pizzaManager;
    private IOrderManager orderManager;

    public SimpleBasketManager(IPizzaManager pizzaManager, IOrderManager orderManager) throws IllegalAccessException, SQLException, InstantiationException {
        basketDao = new DBBasketDao();
        this.pizzaManager = pizzaManager;
        this.orderManager = orderManager;
    }

    @Override
    public void addPizzaToBasket(int userId, String pizzaName, Size size) {
        IPizza pizza = pizzaManager.getPizza(pizzaName, size);
        int pizzaId = pizza.getId();
        double pizzaPrice = pizza.getPrice();

        basketDao.addToBasket(userId, pizzaId, pizzaPrice);
    }

    @Override
    public void deletePizzaFromBasket(int userId, String pizzaName, Size size) {

    }

    @Override
    public long createOrder(int userId, String userAddress, String userPhone) {
        return 0;
    }
}
