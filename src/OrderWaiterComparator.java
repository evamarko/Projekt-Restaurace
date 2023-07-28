import java.util.Comparator;

public class OrderWaiterComparator implements Comparator<Order> {
    @Override
    public int compare(Order p1, Order p2) {
        return p1.getWaiterNumber() - (p2.getWaiterNumber());
    }
}
