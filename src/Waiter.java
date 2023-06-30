public class Waiter {
    private int waiterNumber;
    private int numOfOrders;

    public Waiter(int waiterNumber) {
        this.waiterNumber = waiterNumber;
        this.numOfOrders = 0;
    }

    public int getWaiterNumber() {
        return waiterNumber;
    }

    public void setWaiterNumber(int waiterNumber) {
        this.waiterNumber = waiterNumber;
    }

    public int getNumOfOrders() {
        return numOfOrders;
    }

    public void setNumOfOrders(int numOfOrders) {
        this.numOfOrders = numOfOrders;
    }
}
