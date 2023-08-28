package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO {

    public static String getCustomerNameById(Connection connection, int customerId) throws SQLException {
        String sql = "SELECT name FROM customer WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, customerId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String customerName = resultSet.getString("name");
                    System.out.println("Имя кастомера: " + customerName); // Выводим имя в консоль
                    return customerName;
                }
            }
        }
        return null;
    }


    public static void getOrdersForCustomer(Connection connection, int customerId) throws SQLException {
            String sqlOrders = "SELECT * FROM orders WHERE customerId = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlOrders)) {
                preparedStatement.setInt(1, customerId);

                System.out.println("Все заказы кастомера: ");

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int orderId = resultSet.getInt("id");
                        int cartId = resultSet.getInt("cartId");
                        System.out.println("Order ID: " + orderId + ", Cart ID: " + cartId);
                    }
                }
            }
        }

    public static void getOrdersWithDetailsForCustomer(Connection connection, int customerId) throws SQLException {
        String sqlOrdersDetails = "SELECT o.id AS order_id, o.cartId, cod.products, cod.orderSum " +
                "FROM orders AS o " +
                "JOIN orders_customer_order_details_association AS ocoa ON o.id = ocoa.ordersId " +
                "JOIN customer_orders_details AS cod ON ocoa.customerOrderDetailsId = cod.id " +
                "WHERE o.customerId = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlOrdersDetails)) {
            preparedStatement.setInt(1, customerId);

            System.out.println("Все заказы c деталями кастомера:");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int orderId = resultSet.getInt("order_id");
                    int cartId = resultSet.getInt("cartId");
                    String products = resultSet.getString("products");
                    int orderSum = resultSet.getInt("orderSum");
                    System.out.println("Order ID: " + orderId + ". Cart ID: " + cartId +
                            ". Products: " + products + ". Order Sum: " + orderSum);
                }
            }
        }
    }

    public static void getAllOrdersForCustomers(Connection connection) {
        String sql = "SELECT c.name AS customer_name, o.id AS order_id, o.cartId AS cart_id " +
                "FROM customer c " +
                "JOIN orders o ON c.id = o.customerId";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String customerName = resultSet.getString("customer_name");
                int orderId = resultSet.getInt("order_id");
                int cartId = resultSet.getInt("cart_id");
                System.out.println("Customer Name: " + customerName +
                        ", Order ID: " + orderId +
                        ", Cart ID: " + cartId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getOrderCountPerCustomer(Connection connection) {
        String sql = "SELECT c.name AS customer_name, COUNT(o.id) AS order_count " +
                "FROM customer c " +
                "LEFT JOIN orders o ON c.id = o.customerId " +
                "GROUP BY c.name";

        System.out.println("Кастомеры с количествои их заказов:");

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String customerName = resultSet.getString("customer_name");
                int orderCount = resultSet.getInt("order_count");
                System.out.println("Customer Name: " + customerName +
                        ", Order Count: " + orderCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateOrderDetails(Connection connection, int orderId, String newProducts, int newOrderSum) {
        String sql = "UPDATE customer_orders_details SET products = ?, orderSum = ? WHERE ordersId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newProducts);
            preparedStatement.setInt(2, newOrderSum);
            preparedStatement.setInt(3, orderId);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Order details updated successfully");
            } else {
                System.out.println("Order details update failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void clearCartTable(Connection connection) throws SQLException {
        String deleteDetailsQuery = "DELETE FROM orders_customer_order_details_association WHERE ordersId IN (SELECT id FROM orders);";
        String deleteOrdersQuery = "DELETE FROM orders WHERE cartId IN (SELECT id FROM carts);";
        String deleteCartsQuery = "DELETE FROM carts;";

        try (PreparedStatement deleteDetailsStatement = connection.prepareStatement(deleteDetailsQuery);
             PreparedStatement deleteOrdersStatement = connection.prepareStatement(deleteOrdersQuery);
             PreparedStatement deleteCartsStatement = connection.prepareStatement(deleteCartsQuery)) {

            // Удаляем связанные записи из таблицы "orders_customer_order_details_association"
            deleteDetailsStatement.executeUpdate();
            // Удаляем связанные записи из таблицы "orders"
            deleteOrdersStatement.executeUpdate();
            // Очищаем таблицу "carts"
            deleteCartsStatement.executeUpdate();

            System.out.println("Cart table cleared.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


