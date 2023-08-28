package entity;

public class Order {
    private int customerId;
    private  int cartId;

    public Order(int customerId, int cartId) {
        this.customerId = customerId;
        this.cartId = cartId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }
}
