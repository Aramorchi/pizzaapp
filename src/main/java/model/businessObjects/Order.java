package model.businessObjects;

import model.utils.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Order implements IOrder {
    private static final int DELIVERY_PRICE = 5;
    private static final int MINIMUM_PRICE_FREE_DELIVERY = 18;

    private Status status;
    private List<IPizza> pizzas;
    private String phone;
    private String address;
    private LocalDateTime deadline;
    private LocalDateTime deliveredTime;
    private int price;


    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public void setDeliveredTime(LocalDateTime deliveredTime) {
        this.deliveredTime = deliveredTime;
    }

    @Override
    public void addToOrder(IPizza pizza) {
        if(pizzas == null) {
            pizzas = new ArrayList<>();
        }
        pizzas.add(pizza);
        price += pizza.getPrice();
    }

    @Override
    public void removeFromOrder(IPizza pizza) {
        Logger logger = Logger.getGlobal();
        if(pizzas == null) {
            logger.info("Order is empty!");
        } else if (!pizzas.contains(pizza)) {
            logger.info(String.format("Order doesn't contain %s pizza", pizza.getName()));
        } else {
            pizzas.remove(pizza);
            price -= pizza.getPrice();
        }
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public int getOrderPrice() {
        if(price < MINIMUM_PRICE_FREE_DELIVERY) {
            return price + DELIVERY_PRICE;
        } else {
            return price;
        }
    }

    @Override
    public long getOrderId() {
        return 0;
    }

    @Override
    public Status getStatus() {
        return  status;
    }
}
