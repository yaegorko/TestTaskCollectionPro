package testtask.collectionpro;

public abstract class Order implements Comparable {

    String book;
    String operation;
    double price;
    int volume;
    int orderId;

    public int getOrderId() {
        return orderId;
    }

    public String getBook() {
        return book;
    }

    public String getOperation() {
        return operation;
    }

    public double getPrice() {
        return price;
    }
}
