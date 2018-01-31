package testtask.collectionpro;

import java.util.*;

public class OrderBook {

    String bookName;

    public OrderBook(String bookName) {
        this.bookName = bookName;
    }

    Set<Order> buySet = new TreeSet<>();
    Set<Order> sellSet = new TreeSet<>();
    Map<Integer, Order> allOrders = new HashMap<>();

    public void setOrderToOrderBook(Order order) {
        if (order.getOperation().contains("BUY")) {
            addOrderToOrderBook(order, buySet);
            return;
        } else if (order.getOperation().contains("SELL")) {
            addOrderToOrderBook(order, sellSet);
            return;
        } else if (order.getOperation().contains("DELETE")) {
            deleteOrderFromBook(order);
            return;
        }
    }

    private void addOrderToOrderBook(Order order, Set set) {
        set.add(order);
        allOrders.put(order.getOrderId(), order);
    }

    private void deleteOrderFromBook(Order order) {
        order = allOrders.get(order.getOrderId());
        if (order instanceof BuyOrder && buySet.contains(order)) {
            buySet.remove(order);
        } else if (order instanceof SellOrder && sellSet.contains(order)) {
            sellSet.remove(order);
        }
        allOrders.remove(order);
    }

    private void chekBuyAndSell() {
       Optional<Order> buyOrder = buySet.stream().findFirst();
       Optional<Order> sellOrder = buySet.stream().findFirst();


    }

// Для реализации на Мар
//    Map<Integer, Order> buyMap  = new HashMap<>();
//    Map<Integer, Order> sellMap = new HashMap<>();
//
//    public void setOrderToOrderBook(Order order) {
//        if (order.getOperation().contains("BUY")) {
//            addOrderToOrderBook(order, buyMap);
//
//            return;
//        } else if (order.getOperation().contains("SELL")) {
//            addOrderToOrderBook(order, sellMap);
//            return;
//        } else if (order.getOperation().contains("DELETE")) {
//            deleteOrderFromBook(order.getOrderId(), this);
//            return;
//        }
//    }
//    private void checkMaps() {
//    }
//
//    private void addOrderToOrderBook(Order order, Map map) {
//        map.put(order.getOrderId(), order);
//    }
//
//    private void deleteOrderFromBook(int orderId, OrderBook orderBook) {
//        if (buyMap.containsKey(orderId)) {
//            buyMap.remove(orderId);
//        } else if (sellMap.containsKey(orderId)) {
//            sellMap.remove(orderId);
//        }
//    }

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
