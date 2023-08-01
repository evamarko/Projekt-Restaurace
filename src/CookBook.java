public class CookBook extends DishList {

    public void addDishToCookBook(Dish dish) {
        dishes.add(dish);
        dish.setInCookBook(true);
    }
}
