package model.businessObjects;

import model.utils.Ingredient;
import model.utils.Size;

import java.util.List;

public interface IPizza {
    String getName();
    void setName(String name);
    Size getSize();
    void setSize(Size size);
    int getPrice();
    void setPrice(int price);
    List<Ingredient> getIngredients();
    void setIngredients(List<Ingredient> ingredients);
    void addIngredient(Ingredient ingredient);
    void deleteIngredient(Ingredient ingredient);
}
