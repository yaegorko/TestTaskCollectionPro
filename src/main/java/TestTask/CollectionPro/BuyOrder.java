package testtask.collectionpro;

public class BuyOrder extends Order {

    public BuyOrder(String book, double price, int volume, int orderId) {
        this.book = book;
        this.operation = "BUY";
        this.price = price;
        this.volume = volume;
        this.orderId = orderId;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
