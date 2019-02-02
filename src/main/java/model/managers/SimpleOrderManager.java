package model.managers;

import java.time.LocalDateTime;
import java.util.List;
import model.businessObjects.IPizza;

public class SimpleOrderManager implements IOrderManager{
  
  @Override
  public long createOrder(List<IPizza> pizzas, long userId, LocalDateTime orderCreationTime,
      String address, String phone) {
    return 0;
  }
}
