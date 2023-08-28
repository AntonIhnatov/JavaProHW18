package table_populator;

import entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TablePopulator {

    public static void populateTableCustomer(Connection connection) throws SQLException {
        String insertCustomers = "INSERT INTO Customer (name) VALUES ('Emily'), ('Jacob'), ('Olivia'), ('Ethan'), ('Ava')";
        PreparedStatement insertCustomersStatement = connection.prepareStatement(insertCustomers);
        insertCustomersStatement.execute();

    }

    public static void populateCustomerDetailsTable(Connection connection, List<CustomerDetails> customerDetailsList) throws SQLException {
        String insertCustomerDetails = "INSERT INTO customer_details (customerId, phone, address, sex) VALUES (?, ?, ?, ?);";

        try (PreparedStatement insertCustomerDetailsStatement = connection.prepareStatement(insertCustomerDetails)) {
            for (CustomerDetails customerDetails : customerDetailsList) {
                insertCustomerDetailsStatement.setInt(1, customerDetails.getCustomerId());
                insertCustomerDetailsStatement.setString(2, customerDetails.getPhone());
                insertCustomerDetailsStatement.setString(3, customerDetails.getAddress());
                insertCustomerDetailsStatement.setString(4, customerDetails.getSex());
                insertCustomerDetailsStatement.addBatch();
            }

            insertCustomerDetailsStatement.executeBatch();
        }
    }

    public static void populateCartsTable(Connection connection, List<Cart> carts) throws SQLException {
        String insertCart = "INSERT INTO carts (customerId, quantityOrders) VALUES (?, ?);";

        try (PreparedStatement insertCartStatement = connection.prepareStatement(insertCart)) {
            carts.forEach(cart -> {
                try {
                    insertCartStatement.setInt(1, cart.getCustomerId());
                    insertCartStatement.setInt(2, cart.getQuantityOrders());
                    insertCartStatement.addBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            insertCartStatement.executeBatch();
        }
    }

    public static void populateOrderTable(Connection connection) throws SQLException {
        String insertOrderTable = "INSERT INTO orders (customerId, cartId) VALUES (?, ?);";

        try (PreparedStatement insertOrderTableStatement = connection.prepareStatement(insertOrderTable)) {
            int[] numOrders = {2, 3, 1, 4, 0};

            for (int customerId = 1; customerId <= numOrders.length; customerId++) {
                int cartId = customerId;
                int ordersCount = numOrders[customerId - 1];

                for (int j = 0; j < ordersCount; j++) {
                    insertOrderTableStatement.setInt(1, customerId);
                    insertOrderTableStatement.setInt(2, cartId);
                    insertOrderTableStatement.addBatch();
                }
            }

            insertOrderTableStatement.executeBatch();
        }
    }

    public static void populateOrderDetailsTable(Connection connection, List<OrderDetails> orderDetailsList) throws SQLException {
        String insertOrderDetails = "INSERT INTO customer_orders_details (ordersId, products, orderSum) VALUES (?, ?, ?);";

        try (PreparedStatement insertOrderDetailsStatement = connection.prepareStatement(insertOrderDetails)) {
            orderDetailsList.forEach(orderDetails -> {
                try {
                    insertOrderDetailsStatement.setInt(1, orderDetails.getOrdersId());
                    insertOrderDetailsStatement.setString(2, orderDetails.getProducts());
                    insertOrderDetailsStatement.setInt(3, orderDetails.getOrderSum());
                    insertOrderDetailsStatement.addBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            insertOrderDetailsStatement.executeBatch();
        }
    }

    public static void populateOrderAssociationTable(Connection connection, List<OrderAssociation> orderAssociations) throws SQLException {
        String insertOrderAssociation = "INSERT INTO orders_customer_order_details_association (ordersId, customerOrderDetailsId) VALUES (?, ?);";

        try (PreparedStatement insertOrderAssociationStatement = connection.prepareStatement(insertOrderAssociation)) {
            for (OrderAssociation association : orderAssociations) {
                insertOrderAssociationStatement.setInt(1, association.getOrdersId());
                insertOrderAssociationStatement.setInt(2, association.getCustomerOrderDetailsId());
                insertOrderAssociationStatement.addBatch();
            }
            insertOrderAssociationStatement.executeBatch();
        }
    }
}
