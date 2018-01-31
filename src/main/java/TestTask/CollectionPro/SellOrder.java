package testtask.collectionpro;

import java.util.Objects;

public class SellOrder extends Order implements Comparable<SellOrder> {

    public SellOrder(String book, double price, int volume, int orderId) {
        this.book = book;
        this.operation = "SELL";
        this.price = price;
        this.volume = volume;
        this.orderId = orderId;
    }

    @Override
    public int compareTo(SellOrder o) {
        int result;
        result = Double.compare(price, o.price);
        if(result != 0) return result;
        result = Integer.compare(orderId, o.orderId);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(order.price, price) == 0 &&
                volume == order.volume &&
                orderId == order.orderId &&
                Objects.equals(book, order.book) &&
                Objects.equals(operation, order.operation);
    }

    @Override
    public int hashCode() {

        return Objects.hash(book, operation, price, volume, orderId);
    }
}
