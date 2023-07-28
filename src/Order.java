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

    private int orderNumber;

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

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
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
        return tableNumber + "\t" + orderedTime + "\t" + fulfilmentTime + "\t" + waiterNumber + "\t" + orderedDish + "\t" + numOfOrderedDishes  + "\t" + customer + "\t" + completed;
    }

    //6.  Export seznamu objednávek pro jeden stůl ve formátu (například pro výpis na obrazovku)
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

    /*public Order parseOrder(String data) throws OrderException {
        String [] items = new String[0];
            items = data.split("\t");
            int tableNumber = Integer.parseInt(items[0]);
            LocalDateTime orderedTime = LocalDateTime.parse(items[1]);
            LocalDateTime fulfilmentTime = LocalDateTime.parse(items[2]);
            int waiterNumber = Integer.parseInt(items[3]);
            Dish orderedDish = items[4];

            return new Order(tableNumber, orderedTime, fulfilmentTime, waiterNumber, orderedDish);
    }*/

    @Override
    public String toString() {
        return "Order{" +
                "tableNumber=" + tableNumber +
                ", orderedTime=" + orderedTime +
                ", fulfilmentTime=" + fulfilmentTime +
                ", waiterNumber=" + waiterNumber +
                ", orderedDish=" + orderedDish +
                ", numOfOrderedDishes=" + numOfOrderedDishes +
                ", customer=" + customer +
                ", completed=" + completed +
                ", orderNumber=" + orderNumber +
                '}';
    }
}
