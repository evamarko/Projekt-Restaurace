public class Menu extends DishList {

    public void addDishToMenu(Dish dish) {
        if (dish.getInCookBook()) {
            dishes.add(dish);
            dish.setOnMenu(true);
        } else {
            System.err.println(dish + " nelze zařadit do menu, protože není v zásobníku receptů!");
        }
    }

    public void removeDishes() {
        dishes.removeAll(dishes);
    }
}
