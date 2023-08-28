package entity;

import java.util.List;

public class Cart {
    private int customerId;
    private int quantityOrders;

    public Cart(int customerId, int quantityOrders) {
        this.customerId = customerId;
        this.quantityOrders = quantityOrders;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getQuantityOrders() {
        return quantityOrders;
    }
}
