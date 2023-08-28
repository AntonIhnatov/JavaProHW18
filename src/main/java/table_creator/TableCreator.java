package table_creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableCreator {
    public static void createCustomerTable(Connection connection) throws SQLException {
        String createCustomerTable = "CREATE TABLE IF NOT EXISTS Customer (" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(255)" +
                ");";

        PreparedStatement preparedStatement = connection.prepareStatement(createCustomerTable);
        preparedStatement.execute();

    }

    public static void createCustomerDetailsTable(Connection connection) throws SQLException {
        String createCustomerDetailsTable = "CREATE TABLE IF NOT EXISTS customer_details (" +
                "id SERIAL PRIMARY KEY," +
                "customerId INT," +
                "phone VARCHAR(255)," +
                "address VARCHAR(255)," +
                "sex VARCHAR(10)," +
                "FOREIGN KEY (customerId) REFERENCES Customer(id) ON DELETE CASCADE" +
                ");";

        PreparedStatement preparedStatement = connection.prepareStatement(createCustomerDetailsTable);
        preparedStatement.execute();
    }

    public static void createCartTable(Connection connection) throws SQLException {
        String createCartTable = "CREATE TABLE IF NOT EXISTS carts (" +
                "id SERIAL PRIMARY KEY," +
                "quantityOrders INT," +
                "customerId INT," +
                "FOREIGN KEY (customerId) REFERENCES Customer(id) ON DELETE CASCADE" +
                ");";

        PreparedStatement preparedStatement = connection.prepareStatement(createCartTable);
        preparedStatement.execute();
    }

    public static void createOrderTable(Connection connection) throws SQLException {
        String createOrderTable = "CREATE TABLE IF NOT EXISTS orders (" +
                "id SERIAL PRIMARY KEY," +
                "customerId INT," +
                "cartId INT," +
                "FOREIGN KEY (customerId) REFERENCES customer(id)," +
                "FOREIGN KEY (cartId) REFERENCES carts(id)" +
                ");";

        PreparedStatement preparedStatement = connection.prepareStatement(createOrderTable);
        preparedStatement.execute();

    }
    public static void createOrderDetailsTable(Connection connection) throws SQLException {
        String createOrderDetailsTable = "CREATE TABLE IF NOT EXISTS customer_orders_details (" +
                "id SERIAL PRIMARY KEY," +
                "ordersId INT," +
                "products VARCHAR(255)," +
                "orderSum INT," +
                "FOREIGN KEY (ordersId) REFERENCES orders(id) ON DELETE CASCADE" +
                ");";

        PreparedStatement preparedStatement = connection.prepareStatement(createOrderDetailsTable);
        preparedStatement.execute();
    }

    public static void createOrderAssociation(Connection connection) throws SQLException {
        String createOrderAssociationTable = "CREATE TABLE IF NOT EXISTS orders_customer_order_details_association (" +
                "ordersId INT," +
                "customerOrderDetailsId INT," +
                "PRIMARY KEY (ordersId, customerOrderDetailsId)," +
                "FOREIGN KEY (ordersId) REFERENCES orders(id)," +
                "FOREIGN KEY (customerOrderDetailsId) REFERENCES customer_orders_details(id)" +
                ");";

        try (PreparedStatement preparedStatement = connection.prepareStatement(createOrderAssociationTable)) {
            preparedStatement.execute();
        }
    }
}
