package testtask.collectionpro;

import java.util.*;

public class OrderBook {

    String bookName;

    Map<Double, Order> buyMap = new HashMap<>();
    Map<Double, Order> sellMap = new HashMap<>();
    Map<Integer, Order> allOrders = new HashMap<>();

    public OrderBook(String bookName) {
        this.bookName = bookName;
    }

    public void setOrderToOrderBook(Order order) {
        if (order.getOperation().contains("BUY")) {
            addOrderToOrderBook(order, buyMap);
            return;
        } else if (order.getOperation().contains("SELL")) {
            addOrderToOrderBook(order, sellMap);
            return;
        } else if (order.getOperation().contains("DELETE")) {
            deleteOrderFromBook(order.getOrderId(), this);
            return;
        }
    }

    private void checkMaps() {

    }

    private void addOrderToOrderBook(Order order, Map map) {
        map.put(order.getPrice(), order);
        allOrders.put(order.getOrderId(), order);
    }

    private void deleteOrderFromBook(int orderId, OrderBook orderBook) {

        Order order = allOrders.get(orderId);

        if (buyMap.containsKey(order.getPrice())) {
            buyMap.remove(orderId);
        } else if (sellMap.containsKey(orderId)) {
            sellMap.remove(orderId);
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderBook orderBook = (OrderBook) o;
        return Objects.equals(bookName, orderBook.bookName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookName);
    }
}
