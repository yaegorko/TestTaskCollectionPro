package testtask.collectionpro;

public class DeleteOrder extends Order implements Comparable<DeleteOrder> {

    public DeleteOrder(String book, int orderId) {
        this.book = book;
        this.operation = "DELETE";
        this.orderId = orderId;
    }

    @Override
    public int compareTo(DeleteOrder o) {
        return Integer.compare(orderId, o.orderId);
    }
}
