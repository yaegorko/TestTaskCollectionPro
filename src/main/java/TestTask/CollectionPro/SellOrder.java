package testtask.collectionpro;

public class SellOrder extends Order {

    public SellOrder(String book, double price, int volume, int orderId) {
        this.book = book;
        this.operation = "SELL";
        this.price = price;
        this.volume = volume;
        this.orderId = orderId;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
