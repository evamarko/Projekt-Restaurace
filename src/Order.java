import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private int tableNumber;
    private LocalDateTime orderedTime;
    private LocalDateTime fulfilmentTime;
    private int waiterNumber;
    private Dish orderedDish;
    private int numOfOrderedDishes = 1;
    private Customer customer;
    private Boolean completed = false;
    DishList dishList = new DishList();
    public Order() {
    }

    public Order(int tableNumber, LocalDateTime orderedTime, int waiterNumber, Dish orderedDish) {
        this.tableNumber = tableNumber;
        this.orderedTime = orderedTime;
        this.waiterNumber = waiterNumber;
        this.orderedDish = orderedDish;
    }

    public Order(int tableNumber, LocalDateTime orderedTime, LocalDateTime fulfilmentTime, int waiterNumber, Dish orderedDish) {
        this (tableNumber, orderedTime, waiterNumber, orderedDish);
        setFulfilmentTime(fulfilmentTime);
    }

    public Order(int tableNumber, LocalDateTime orderedTime, LocalDateTime fulfilmentTime, int waiterNumber, Dish orderedDish, int numOfOrderedDishes) {
        this (tableNumber, orderedTime, fulfilmentTime, waiterNumber, orderedDish);
        this.numOfOrderedDishes = numOfOrderedDishes;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(LocalDateTime orderedTime) {
        this.orderedTime = orderedTime;
    }

    public LocalDateTime getFulfilmentTime() {
        return fulfilmentTime;
    }

    public void setFulfilmentTime(LocalDateTime fulfilmentTime) {
        this.fulfilmentTime = fulfilmentTime;
        this.completed = true;
    }

    public int getWaiterNumber() {
        return waiterNumber;
    }

    public void setWaiterNumber(int waiterNumber) {
        this.waiterNumber = waiterNumber;
    }


    public Dish getOrderedDish() {
        return orderedDish;
    }

    public void setOrderedDish(Dish orderedDish) {
        this.orderedDish = orderedDish;
    }

    public int getNumOfOrderedDishes() {
        return numOfOrderedDishes;
    }

    public void setNumOfOrderedDishes(int numOfOrderedDishes) {
        this.numOfOrderedDishes = numOfOrderedDishes;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getNumberOfDishesIfBiggerThenOne() {
        String result = "";
        if (numOfOrderedDishes > 1) {
            result = numOfOrderedDishes + "x";
        }
        return result;
    }

    public String formatTime(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public String exportToString() {
        return tableNumber + "\t" + orderedTime + "\t" + fulfilmentTime + "\t" + waiterNumber + "\t" + orderedDish + "\t" + numOfOrderedDishes;
    }

    public String orderToPrint() {
        return "." + " "
                + orderedDish + " "
                + getNumberOfDishesIfBiggerThenOne() + " " + "(" + (orderedDish.getPrice().multiply(BigDecimal.valueOf(numOfOrderedDishes))) + " Kč" + ")"
                + ":" + "\t"
                + formatTime(orderedTime)
                + "–"
                + formatTime(fulfilmentTime) + "\t"
                + "číšník č. " + waiterNumber;
    }

    public Order parseOrder(String data) throws OrderException {
        String [] items;
        items = data.split("\t");
        int tableNumber = Integer.parseInt(items[0]);
        LocalDateTime orderedTime = LocalDateTime.parse(items[1]);
        LocalDateTime fulfilmentTime = LocalDateTime.parse(items[2]);
        int waiterNumber = Integer.parseInt(items[3]);
        Dish orderedDish = dishList.getDishObject(items[4]);
        int numOfOrderedDishes = Integer.parseInt(items[5]);
        return new Order(tableNumber, orderedTime, fulfilmentTime, waiterNumber, orderedDish, numOfOrderedDishes);
    }

    @Override
    public String toString() {
        return "Order{" +
                "tableNumber=" + tableNumber +
                ", orderedTime=" + orderedTime +
                ", orderedDish=" + orderedDish +
                '}';
    }
}
