import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class RestaurantManager {
    private List<Order> orders = new ArrayList<>();
    Order order = new Order();

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        if (order.getOrderedDish().getOnMenu()) {
            orders.add(order);
        } else {
            System.err.println(order.getOrderedDish() + " nelze objednat, protože jídlo není na menu!");
        }
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    //1. Kolik objednávek je aktuálně rozpracovaných a nedokončených.
    public String getUncompletedOrders() {
        int numOfUncompletedOrders = 0;
        for (Order order : orders) {
            if (!order.getCompleted()) {
                numOfUncompletedOrders++;
            }
        }
        return "Počet rozpracovaných a nedokončených objednávek: " + numOfUncompletedOrders;
    }

    //2. Možnost seřadit objednávky podle číšníka nebo času zadání.
    public void sortOrdersByTime() {
        Collections.sort(orders, new OrderTimeComparator());
    }

    public void sortOrdersByWaiter() {
        Collections.sort(orders, new OrderWaiterComparator());
    }

    //3. Celkovou cenu objednávek pro jednotlivé číšníky (u každého číšníka bude počet jeho zadaných objednávek).
    public BigDecimal getOrderPricePerWaiter(int waiterNumber) {
        BigDecimal orderPrice = BigDecimal.ZERO;
        for (Order order : orders) {
            if (order.getWaiterNumber() == waiterNumber) {
                orderPrice = orderPrice.add(order.getOrderedDish().getPrice());
            }
        }
        return orderPrice;
    }

    public int getNumOfOrdersPerWaiter(int waiterNumber) {
        int numOfOrders = 0;
        for (Order order : orders) {
            if (order.getWaiterNumber() == waiterNumber) {
                numOfOrders = numOfOrders + 1;
            }
        }
        return numOfOrders;
    }

    //4. Průměrnou dobu zpracování objednávek, které byly zadány v určitém časovém období.
    public long averageTimeToCompleteOrderBetweenGivenTimes(LocalDateTime time1, LocalDateTime time2) throws OrderException {
        long timeToCompleteAllOrders = 0;
        long timeToCompleteOrder;
        int numOfOrders = 0;
        try {
            for (Order order : orders) {
                if (order.getOrderedTime().isAfter(time1) && order.getOrderedTime().isBefore(time2) && order.getCompleted()) {
                    timeToCompleteOrder = ChronoUnit.MINUTES.between(order.getOrderedTime(), order.getFulfilmentTime());
                    numOfOrders = numOfOrders + 1;
                    timeToCompleteAllOrders = timeToCompleteAllOrders + timeToCompleteOrder;
                }
            }
            return timeToCompleteAllOrders / numOfOrders;
        } catch (ArithmeticException e) {
            throw new OrderException("V daném časovém období nebyly zadané žádné objednávky!");
        }
    }

    //5. Seznam jídel, které byly dnes objednány. Bez ohledu na to, kolikrát byly objednány.
    public void getOrderedDishesToday() {
        Set<Dish> setOfDishes = new HashSet<>();
        for (Order order : orders) {
            if (order.getOrderedTime().getDayOfMonth() == LocalDateTime.now().getDayOfMonth()) {
                setOfDishes.add(order.getOrderedDish());
            }
        }
        System.out.println(setOfDishes);
    }

    //6. Export seznamu objednávek pro jeden stůl ve formátu (například pro výpis na obrazovku)
    public void getOrdersPerTable(int tableNumber) {
        int orderNumber = 1;
        if (tableNumber < 9) {
            System.out.println("** Objednávky pro stůl č. " + " " + tableNumber);
        } else {
            System.out.println("** Objednávky pro stůl č. " + tableNumber);
        }
        System.out.println("*******");
        for (Order order : orders) {
            if (order.getTableNumber() == tableNumber) {
                System.out.println(orderNumber + order.orderToPrint());
                orderNumber += 1;
                }
        }
        System.out.println("*******");
    }

    public void exportToFile(String fileName) throws OrderException {
        try (PrintWriter outputWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Order order : orders) {
                outputWriter.println(order.exportToString());
            }
        } catch (IOException e) {
            throw new OrderException("Nepodařilo se nahrát data do souboru: " + fileName);
        }
    }

    public void importFromFile(String fileName) throws OrderException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                orders.add(order.parseOrder(line)); }
        } catch (FileNotFoundException e) {
            throw new OrderException("Soubor " + fileName + " nalezen!");
        }
    }
}