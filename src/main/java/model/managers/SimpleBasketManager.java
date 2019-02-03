package model.managers;

import model.businessObjects.IBasket;
import model.businessObjects.IPizza;
import model.dao.DBBasketDao;
import model.utils.Size;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<String> getBasketItems(int userId) {
        IBasket basket = basketDao.getBasketOfUser(userId);
        List<String> pizzas = basket.getBasketContent().keySet().stream()
                .map(key -> pizzaManager.getPizzaById(key))
                .map(IPizza::getName)
                .collect(Collectors.toList());
        return pizzas;
    }

    @Override
    public void deletePizzaFromBasket(int userId, String pizzaName, Size size) {
        basketDao.removeFromBasket(userId, pizzaManager.getPizza(pizzaName, size).getId());
    }

    @Override
    public void createOrder(int userId, String userAddress, String userPhone) {
        IBasket userBasket = basketDao.getBasketOfUser(userId);
        List<IPizza> pizzas = new ArrayList<>();
        userBasket.getBasketContent().keySet().forEach(key -> pizzas.add(pizzaManager.getPizzaById(key)));
        orderManager.createOrder(pizzas, userId, LocalDateTime.now(), userAddress, userPhone);
        basketDao.deleteUserBasket(userId);
    }
}
