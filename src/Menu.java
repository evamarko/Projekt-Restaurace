public class Menu extends DishList {

    //Do menu (menu) zařazujeme jídla ze zásobníku receptů.
    // Počet není omezen, ale bude nejspíš mnohem nižší, než je počet jídel v zásobníku.
    public void addDishToMenu(Dish dish) {
        if (dish.getInCookBook()) {
            dishes.add(dish);
            dish.setOnMenu(true);
        } else {
            System.err.println(dish + " nelze zařadit do menu, protože není v zásobníku receptů!");
        }
    }

    //Kuchař má možnost všechna jídla z menu vymazat naráz.
    public void removeDishes() {
        dishes.removeAll(dishes);
    }
}
