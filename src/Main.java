import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        CookBook cookBookDishes = new CookBook();
        Menu menuDishes = new Menu();
        RestaurantManager restaurantOrders = new RestaurantManager();

        //Testovací scénář
        //1. Načti stav evidence z disku.
        try {
            cookBookDishes.exportToFile("recepty.txt");
        } catch (OrderException e) {
            System.err.println(e.getLocalizedMessage());
        }

        try {
            menuDishes.exportToFile("menu.txt");
        } catch (OrderException e) {
            System.err.println(e.getLocalizedMessage());
        }

        try {
            restaurantOrders.exportToFile("objednavky.txt");
        } catch (OrderException e) {
            System.err.println(e.getLocalizedMessage());
        }

        //2. Připrav testovací data. Vlož do systému:
        //Tři jídla:
        Dish kureciRizek = new Dish("Kuřecí řízek obalovaný 150 g", BigDecimal.valueOf(230), 15, Category.MAIN);
        Dish hranolky = new Dish("Hranolky 150 g", BigDecimal.valueOf(80), 5, Category.MAIN);
        Dish pstruh = new Dish("Pstruh na víně 200 g", BigDecimal.valueOf(270), 20, Category.MAIN);

        //První a třetí jídlo zařaď do aktuálního menu, druhé jídlo nikoli.
        cookBookDishes.addDishToCookBook(kureciRizek);
        cookBookDishes.addDishToCookBook(hranolky);
        cookBookDishes.addDishToCookBook(pstruh);

        menuDishes.addDishToMenu(kureciRizek);
        menuDishes.addDishToMenu(pstruh);

        //Vytvoř alespoň tři objednávky pro stůj číslo 15 a jednu pro stůj číslo 2.
        //Objednávky řeší alespoň dva různí číšníci.
        //Min. dvě objednávky jsou již uzavřené, minimálně dvě ještě nikoli.
        Order order1 = new Order(15, LocalDateTime.of(2023, 7, 6, 14, 00), LocalDateTime.of(2023, 7, 6, 14, 15), 1, pstruh, 1);
        Order order2 = new Order(15, LocalDateTime.of(2023, 7, 6, 14, 00), LocalDateTime.of(2023, 7, 6, 14, 15), 2, pstruh, 1);

        Order order3 = new Order(15, LocalDateTime.of(2023, 7, 6, 14, 10), 1, kureciRizek, 1);
        Order order4 = new Order(2, LocalDateTime.of(2023, 7, 6, 14, 5), 2, pstruh, 1);

        restaurantOrders.addOrder(order1);
        restaurantOrders.addOrder(order2);
        restaurantOrders.addOrder(order3);
        restaurantOrders.addOrder(order4);

        //3. Vyzkoušej přidat objednávku jídla, které není v menu — aplikace musí ohlásit chybu.
        Order order5 = new Order(2, LocalDateTime.of(2023, 7, 6, 14, 5), 1, hranolky, 1);
        restaurantOrders.addOrder(order5);

        //4. Proveď uzavření objednávky.
        order3.setFulfilmentTime(LocalDateTime.of(2023, 7,6, 14, 20));
        order4.setFulfilmentTime(LocalDateTime.of(2023, 7,6, 14, 20));

        //5. Použij všechny připravené metody pro získání informací pro management — údaje vypisuj na obrazovku.
        //5.1. Kolik objednávek je aktuálně rozpracovaných a nedokončených.
        System.out.println(restaurantOrders.getUncompletedOrders());

        //5.2. Možnost seřadit objednávky podle číšníka nebo času zadání.
        restaurantOrders.sortOrdersByTime();
        restaurantOrders.getOrders().forEach(c -> System.out.println(c.getOrderedDish() + ": " + c.getOrderedTime()));

        restaurantOrders.sortOrdersByWaiter();
        restaurantOrders.getOrders().forEach(c -> System.out.println( "Číšník č. " + c.getWaiterNumber() + ": " + c.getOrderedDish()));

        //5.3. Celkovou cenu objednávek pro jednotlivé číšníky (u každého číšníka bude počet jeho zadaných objednávek).
        System.out.println(restaurantOrders.getOrderPricePerWaiter(2));
        System.out.println(restaurantOrders.getNumOfOrdersPerWaiter(2));

        //5.4. Průměrnou dobu zpracování objednávek, které byly zadány v určitém časovém období.
        try {
            System.out.println(restaurantOrders.averageTimeToCompleteOrderBetweenGivenTimes(LocalDateTime.of(2023, 7, 6, 13, 00), LocalDateTime.of(2023, 7, 6, 15, 00)));
        } catch (OrderException e) {
            System.err.println(e.getLocalizedMessage());
        }

        //5.5. Seznam jídel, které byly dnes objednány. Bez ohledu na to, kolikrát byly objednány.
        restaurantOrders.getOrderedDishesToday();

        //5.6.  Export seznamu objednávek pro jeden stůl ve formátu (například pro výpis na obrazovku)
        restaurantOrders.getOrdersPerTable(15);

        //6. Změněná data ulož na disk. Po spuštění aplikace musí být data opět v pořádku načtena.
        try {
            cookBookDishes.exportToFile("recepty.txt");
        } catch (OrderException e) {
            System.err.println(e.getLocalizedMessage());
        }

        try {
            menuDishes.exportToFile("menu.txt");
        } catch (OrderException e) {
            System.err.println(e.getLocalizedMessage());
        }

        try {
            restaurantOrders.exportToFile("objednavky.txt");
        } catch (OrderException e) {
            System.err.println(e.getLocalizedMessage());
        }

        // 9. Připrav do složky projektu poškozený vstupní soubor/poškozené vstupní soubory, které se nepodaří načíst.
        //Aplikace se při spuštění s těmito soubory musí zachovat korektně — nesmí spadnout.
        //10.Pokud tyto soubory posléze smažeme, aplikace musí fungovat a můžeme pokračovat v testování.
        try {
            menuDishes.importFromFile("nove-recepty.txt");
        } catch (OrderException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}

