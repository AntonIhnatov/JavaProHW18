package entity;

public class OrderDetails {
    private int ordersId;
    private String products;
    private int orderSum;

    public OrderDetails(int ordersId, String products, int orderSum) {
        this.ordersId = ordersId;
        this.products = products;
        this.orderSum = orderSum;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public String getProducts() {
        return products;
    }

    public int getOrderSum() {
        return orderSum;
    }
}
