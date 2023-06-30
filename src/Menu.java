import java.util.ArrayList;
import java.util.List;

public class Menu extends CookBook {
    List<Dish> dishes = new ArrayList<>();

    public void removeDishes() {
        dishes.removeAll(dishes);
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
