package model.facade;

import java.sql.SQLException;
import model.businessObjects.IPizza;
import model.managers.IBasketManager;
import model.managers.IOrderManager;
import model.managers.IPizzaManager;
import model.managers.IUserManager;
import model.managers.SimpleBasketManager;
import model.managers.SimpleOrderManager;
import model.managers.SimplePizzaManager;
import model.managers.SimpleUserManager;
import model.utils.Size;

import java.util.List;
import java.util.stream.Collectors;

public class SimpleFacade implements IFacade {
    private static SimpleFacade simpleFacade;
    
    private IUserManager userManager;
    private IPizzaManager pizzaManager;
    private IBasketManager basketManager;
    private IOrderManager orderManager;
    
    public static SimpleFacade getInstance()
        throws IllegalAccessException, SQLException, InstantiationException {
        if(simpleFacade == null) {
            simpleFacade = new SimpleFacade();
        }
        return simpleFacade;
    }

    private SimpleFacade() throws IllegalAccessException, SQLException, InstantiationException {
        userManager = new SimpleUserManager();
        pizzaManager = new SimplePizzaManager();
        orderManager = new SimpleOrderManager();
        basketManager = new SimpleBasketManager(pizzaManager, orderManager);
    }
    
    public List<String> getPizzaNames() {
        return pizzaManager.getAllPizzas().stream().map(IPizza::getName).collect(Collectors.toList());
    }

    public void addPizzaToBasket(String pizzaName) {
        int userId = userManager.getCurrentUserId();
        basketManager.addPizzaToBasket(userId, pizzaName, Size.MEDIUM);
    }

    public void logIn(String login, String password) {
        userManager.logIn(login, password);
    }

    public void registerNewUser(String login, String password) {
        userManager.register(login, password);
    }

    public String getCurrentUsername() {
        return userManager.getCurrentUsername();
    }

    public void registerNewUser(String login, String password, String phone) {
        userManager.register(login, password, phone);
    }

    public void logOut() {
        userManager.logOut();
    }

    public void confirmOrder(String address, String phone) {
        basketManager.createOrder(userManager.getCurrentUserId(), address, phone);
    }

    public void confirmOrder(String address) {
        basketManager.createOrder(userManager.getCurrentUserId(), address, userManager.getCurrentUserPhone());
    }
}
