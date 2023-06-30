import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        CookBook cookBookDishes = new CookBook();
        Menu menuDishes = new Menu();
        RestaurantManager restaurantOrders = new RestaurantManager();

        Dish fishAndChips = new Dish("Fish and Chips", BigDecimal.valueOf(150), 10,"fish-and-chips-01", List.of("fish-and-chips-02"), Category.MAIN);
        Dish nachos = new Dish ("Nachos", BigDecimal.valueOf(130), 8, "nachos-01", List.of("nachos-O2"), Category.MAIN);
        Dish schnitzel = new Dish("Schnitzel", BigDecimal.valueOf(180), 12, "schnitzel-01", List.of("schnitzel-02"), Category.MAIN);
        Table table1 = new Table(1);
        Table table2 = new Table(2);
        Waiter waiter1 = new Waiter (1);
        Waiter waiter2 = new Waiter (2);
        Order order1;
        try {
            order1 = new Order(table1, LocalDateTime.of(2023, 6, 30, 14, 15), waiter1, fishAndChips);
        } catch (OrderException e) {
            throw new RuntimeException(e);
        }
        order1.setFulfilmentTime(LocalDateTime.of(2023, 6, 30, 14, 30));

        Order order2;
        try {
            order2 = new Order(table1, LocalDateTime.of(2023, 6, 30, 14, 10), waiter1, nachos);
        } catch (OrderException e) {
            throw new RuntimeException(e);
        }
        order2.setFulfilmentTime(LocalDateTime.of(2023, 6, 30, 14, 45));

        cookBookDishes.addDish(fishAndChips);
        cookBookDishes.addDish(nachos);
        menuDishes.addDish(fishAndChips);
        menuDishes.addDish(schnitzel);
        restaurantOrders.addOrder(order1);
        restaurantOrders.addOrder(order2);

        try {
            cookBookDishes.exportToFile("recepty.txt");
        } catch (OrderException e) {
            System.err.println("Problém při nahrávání dat do souboru! " + e.getLocalizedMessage());
        }
        try {
            menuDishes.exportToFile("menu.txt");
        } catch (OrderException e) {
            System.err.println("Problém při nahrávání dat do souboru! " + e.getLocalizedMessage());
        }
        try {
            restaurantOrders.exportToFile("objednavky.txt");
        } catch (OrderException e) {
            System.err.println("Problém při nahrávání dat do souboru! " + e.getLocalizedMessage());
        }

        //1. Kolik objednávek je aktuálně rozpracovaných a nedokončených.
        System.out.println(restaurantOrders.getCompletedAndUncompletedOrders());

        //2. Možnost seřadit objednávky podle času zadání.
        restaurantOrders.sortOrdersByTime();
        //restaurantOrders.getOrders().forEach(c -> System.out.println(c.getOrderedDishes() + ": " + c.getOrderedTime()));

        //3. Celkovou cenu objednávek pro jednotlivé číšníky (u každého číšníka bude počet jeho zadaných objednávek).
        System.out.println(restaurantOrders.getOrderPricePerWaiter(waiter1));

        //4. Průměrnou dobu zpracování objednávek, které byly zadány v určitém časovém období.
        System.out.println(restaurantOrders.averageTimeToCompleteOrderBetweenGivenTimes(LocalDateTime.of(2023, 6, 30, 14, 00), LocalDateTime.of(2023, 6, 30, 15, 00)));

        //5. Seznam jídel, které byly dnes objednány. Bez ohledu na to, kolikrát byly objednány.
        restaurantOrders.getOrderedDishesToday();

    }
}

//Doplnit do aplikace:
//Má také jít přidat nebo odebrat fotografie, vždy by ale alespoň jedna měla zůstat.
//Do menu (menu) zařazujeme jídla ze zásobníku receptů.
//Objednávat jdou pouze jídla, která jsou v aktuálním menu — ostatní jídla ze zásobníku objednat nelze.
//Možnost seřadit objednávky podle číšníka.
