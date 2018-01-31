package testtask.collectionpro;

public class DeleteOrder extends Order {

    public DeleteOrder(String book, int orderId) {
        this.book = book;
        this.operation = "DELETE";
        this.orderId = orderId;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
