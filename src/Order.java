import java.time.LocalDateTime;

public class Order {
    private Table table;
    private LocalDateTime orderedTime;
    private LocalDateTime fulfilmentTime;
    private Waiter waiter;
    private Dish orderedDish;
    private String notes;
    private Boolean completed = false;


    public Order(Table table, LocalDateTime orderedTime, Waiter waiter, Dish orderedDish, String notes) throws OrderException {
        this.table = table;
        this.orderedTime = orderedTime;
        this.waiter = waiter;
        this.orderedDish = orderedDish;
        this.notes = notes;
        this.waiter.setNumOfOrders(this.waiter.getNumOfOrders() + 1);
    }

    public Order(Table table, LocalDateTime orderedTime, Waiter waiter, Dish orderedDish) throws OrderException {
        this (table, orderedTime, waiter, orderedDish, "");
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
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

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public Dish getOrderedDish() {
        return orderedDish;
    }

    public void setOrderedDishes(Dish orderedDish) {
        this.orderedDish = orderedDish;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String exportToString() {
        return table.getTableNumber() + "\t" + orderedTime + "\t" + fulfilmentTime + "\t" + waiter.getWaiterNumber() + "\t" + orderedDish + "\t" + notes + "\t" + completed;
    }

    @Override
    public String toString() {
        return "Order{" +
                "table=" + table +
                ", orderedTime=" + orderedTime +
                ", fulfilmentTime=" + fulfilmentTime +
                ", waiter=" + waiter +
                ", orderedDish=" + orderedDish +
                ", notes='" + notes + '\'' +
                ", completed=" + completed +
                '}';
    }
}
