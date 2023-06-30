import java.util.Comparator;

public class OrderTimeComparator implements Comparator<Order> {
    @Override
    public int compare(Order p1, Order p2) {
        return p1.getOrderedTime().compareTo(p2.getOrderedTime());
    }
}