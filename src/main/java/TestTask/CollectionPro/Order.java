package testtask.collectionpro;

public abstract class Order{

    String book;
    String operation;
    double price;
    int volume;

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

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
