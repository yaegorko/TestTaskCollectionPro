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
            checkBuyAndSell();
            return;
        } else if (order.getOperation().contains("SELL")) {
            addOrderToOrderBook(order, sellSet);
            checkBuyAndSell();
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

    private void checkBuyAndSell() {
       Optional<? extends Order> buyOrder  = buySet.stream().findFirst();
       Optional<? extends Order> sellOrder = sellSet.stream().findFirst();
       if (!buyOrder.isPresent() || !sellOrder.isPresent()) {
           return;
       }
       if (!isPriceEqual((BuyOrder) buyOrder.get(),(SellOrder) sellOrder.get())) {
           return;
       }
       if (isEnoughVolumesForBuy((BuyOrder) buyOrder.get(),(SellOrder) sellOrder.get())){
           int i = sellOrder.get().getVolume() - buyOrder.get().getVolume();
           sellOrder.get().setVolume(i);
//           System.out.println("Ордер " + ((SellOrder) sellOrder.get()).getOrderId()
//                                + " на " + ((SellOrder) sellOrder.get()).getOperation()
//                                + " " + ((SellOrder) sellOrder.get()).getVolume()
//                                + "выполнен");
           deleteOrderFromBook(buyOrder.get());
           if (sellOrder.get().getVolume() == 0) {
               deleteOrderFromBook(sellOrder.get());
           }
       } else {
           int i = buyOrder.get().getVolume() - sellOrder.get().getVolume();
           buyOrder.get().setVolume(i);
           deleteOrderFromBook(sellOrder.get());
           checkBuyAndSell();
       }
    }

    private boolean isPriceEqual(BuyOrder buyOrder, SellOrder sellOrder) {
        return buyOrder.getPrice() >= sellOrder.getPrice();
    }

    private boolean isEnoughVolumesForBuy(BuyOrder buyOrder, SellOrder sellOrder) {
        return sellOrder.getVolume() >= buyOrder.getVolume();
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
