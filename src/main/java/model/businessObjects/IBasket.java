package model.businessObjects;

public interface IBasket {
    void clearBasket();
    void addToBasket(IPizza pizza);
    void deleteFromBasket(IPizza pizza);

    int getPrice();
}
