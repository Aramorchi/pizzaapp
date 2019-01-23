package model.businessObjects;

import model.utils.Ingredient;
import model.utils.Size;

import java.util.List;

public class Pizza implements IPizza {
    private int id;
    private String name;
    private Size size;
    private int price;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public List<Ingredient> getIngredients() {
        return null;
    }

    @Override
    public void setIngredients(List<Ingredient> ingredients) {

    }

    @Override
    public void addIngredient(Ingredient ingredient) {

    }

    @Override
    public void deleteIngredient(Ingredient ingredient) {

    }
}
