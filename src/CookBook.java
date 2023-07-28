public class CookBook extends DishList {

    //Kuchaři mají možnost některá jídla ze zásobníku vyřadit, přidat, nebo upravit.
    public void addDishToCookBook(Dish dish) {
        dishes.add(dish);
        dish.setInCookBook(true);
    }
}
