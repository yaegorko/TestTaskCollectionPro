package TestTask.CollectionPro;

import java.util.*;

public class OrderBook {

    String bookName;

    Set<Order> buySet = new TreeSet<>();
    Set<Order> sellSet = new TreeSet<>();

    public OrderBook(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderBook orderBook = (OrderBook) o;
        return Objects.equals(bookName, orderBook.bookName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookName);
    }

}
