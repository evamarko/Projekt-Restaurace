import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class RestaurantManager {
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
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

    //1. Kolik objednávek je aktuálně rozpracovaných a nedokončených.
    public String getCompletedAndUncompletedOrders() {
        int numOfUncompletedOrders = 0;
        for (Order order : orders) {
            if (!order.getCompleted()) {
                numOfUncompletedOrders++;
            }
        }
        return "Počet rozpracovaných a nedokončených objednávek: " + numOfUncompletedOrders;
    }

    //2. Možnost seřadit objednávky podle času zadání.
    public void sortOrdersByTime() {
        Collections.sort(orders, new OrderTimeComparator());
    }

    //3. Celkovou cenu objednávek pro jednotlivé číšníky (u každého číšníka bude počet jeho zadaných objednávek).
    public BigDecimal getOrderPricePerWaiter(Waiter waiter) {
        BigDecimal orderPrice = BigDecimal.ZERO;
        for (Order order : orders) {
            if (order.getWaiter() == waiter) {
                orderPrice = orderPrice.add(order.getOrderedDish().getPrice());
                /*for (Dish dish : order.getOrderedDish()) {
                    orderPrice = orderPrice.add(dish.getPrice());
                }*/
            }
        }
        return orderPrice;
    }

    //4. Průměrnou dobu zpracování objednávek, které byly zadány v určitém časovém období.
    public long averageTimeToCompleteOrderBetweenGivenTimes(LocalDateTime time1, LocalDateTime time2) {
        long timeToCompleteAllOrders = 0;
        long timeToCompleteOrder;
        int numOfOrders = 0;
        for (Order order : orders) {
            if (order.getOrderedTime().isAfter(time1) && order.getOrderedTime().isBefore(time2)) {
                timeToCompleteOrder = ChronoUnit.MINUTES.between(order.getOrderedTime(), order.getFulfilmentTime());
                numOfOrders = numOfOrders + 1;
                timeToCompleteAllOrders = timeToCompleteAllOrders + timeToCompleteOrder;
            }
        }
        return timeToCompleteAllOrders / numOfOrders;
    }

    //5. Seznam jídel, které byly dnes objednány. Bez ohledu na to, kolikrát byly objednány.
    public void getOrderedDishesToday() {
        Set<Dish> setOfDishes = new HashSet<>();
        for (Order order : orders) {
            if (order.getOrderedTime().getDayOfMonth() == LocalDateTime.now().getDayOfMonth()) {
                setOfDishes.add(order.getOrderedDish());
               /*for (Dish dish : order.getOrderedDishes()) {
                   setOfDishes.add(dish);
               }*/
            }
        }
        System.out.println(setOfDishes);
    }

}

