package entity;

public class OrderAssociation {
    private int ordersId;
    private int customerOrderDetailsId;

    public OrderAssociation(int ordersId, int customerOrderDetailsId) {
        this.ordersId = ordersId;
        this.customerOrderDetailsId = customerOrderDetailsId;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public int getCustomerOrderDetailsId() {
        return customerOrderDetailsId;
    }
}