import entity.*;

import java.sql.*;
import java.util.List;


public class Main {
    public static void main(String[] args) {
            try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/HW18",
                    "postgres", "Louise@444")) {

                List<CustomerDetails> customerDetailsList = List.of(
                        new CustomerDetails(1, "1234567890", "123 Main Street. Springfield, CA 12345, USA", "Female"),
                        new CustomerDetails(2, "2345678901", "456 Oak Avenue, Lexington, NY 67890, USA", "Male"),
                        new CustomerDetails(3, "3456789012", "789 Maple Lane, Austin, TX 98765, USA", "Female"),
                        new CustomerDetails(4, "4567890123", "987 Elm Street, Denver, CO 54321, USA", "Male"),
                        new CustomerDetails(5, "5678901234", "543 Pine Road,Seattle, WA 23456, USA", "Female")
                );

                List<Cart> customerCartList = List.of(
                        new Cart(1, 2),
                        new Cart(2, 3),
                        new Cart(3, 4),
                        new Cart(4, 1),
                        new Cart(5, 0)
                );

                List<OrderDetails> orderDetailsList = List.of(
                        new OrderDetails(1, "chocolate", 12),
                        new OrderDetails(2, "bread", 5),
                        new OrderDetails(3, "milk", 6),
                        new OrderDetails(4, "eggs, cheese", 32),
                        new OrderDetails(5, "apple", 4),
                        new OrderDetails(6, "orange juice", 8),
                        new OrderDetails(7, "chocolate, bread, milk", 23),
                        new OrderDetails(8, "meat, sausage, spices", 65),
                        new OrderDetails(9, "sausage", 20),
                        new OrderDetails(10, "garlic", 2)
                );

                List<OrderAssociation> orderAssociationList = List.of(
                        new OrderAssociation(1, 1),
                        new OrderAssociation(2, 2),
                        new OrderAssociation(3, 3),
                        new OrderAssociation(4, 4),
                        new OrderAssociation(5, 5),
                        new OrderAssociation(6, 6),
                        new OrderAssociation(7, 7),
                        new OrderAssociation(8, 8),
                        new OrderAssociation(9, 9),
                        new OrderAssociation(10, 10)
                        );

//            TableCreator.createCustomerTable(connection);
//            TableCreator.createCustomerDetailsTable(connection);
//            TableCreator.createCartTable(connection);
//            TableCreator.createOrderTable(connection);
//            TableCreator.createOrderDetailsTable(connection);
//            TableCreator.createOrderAssociation(connection);
//            TablePopulator.populateTableCustomer(connection);
//            TablePopulator.populateCustomerDetailsTable(connection, customerDetailsList);
//            TablePopulator.populateCartsTable(connection, customerCartList);
//            TablePopulator.populateOrderTable(connection);
//            TablePopulator.populateOrderDetailsTable(connection, orderDetailsList);
//            TablePopulator.populateOrderAssociationTable(connection, orderAssociationList);

//                OrderDAO.getCustomerNameById(connection, 3);
//                System.out.println("----------------------");
//                OrderDAO.getOrdersForCustomer(connection, 3);
//                System.out.println("----------------------");
//                OrderDAO.getOrdersWithDetailsForCustomer(connection, 3);
//                System.out.println("----------------------");
//                OrderDAO.getAllOrdersForCustomers(connection);
//                System.out.println("----------------------");
//                OrderDAO.getOrderCountPerCustomer(connection);
//
//                OrderDAO.updateOrderDetails(connection, 6, "pineapple", 15);
//                System.out.println("--------------------------");
//                OrderDAO.getOrdersWithDetailsForCustomer(connection, 3);

//                OrderDAO.clearCartTable(connection);

//                dropAllTables(connection);

            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }

    public static void dropAllTables(Connection connection) throws SQLException {
        String dropOrderOrderDetailsTable = "DROP TABLE IF EXISTS orders_customer_order_details_association;";
        String dropOrderDetailsTable = "DROP TABLE IF EXISTS customer_order_details;";
        String dropOrderTable = "DROP TABLE IF EXISTS orders CASCADE;"; // Каскадное удаление
        String dropCartTable = "DROP TABLE IF EXISTS carts CASCADE;"; // Каскадное удаление
        String dropCustomerDetailsTable = "DROP TABLE IF EXISTS customer_details;";
        String dropCustomerTable = "DROP TABLE IF EXISTS customer;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(dropOrderOrderDetailsTable)) {
            preparedStatement.execute();
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(dropOrderDetailsTable)) {
            preparedStatement.execute();
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(dropOrderTable)) {
            preparedStatement.execute();
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(dropCartTable)) {
            preparedStatement.execute();
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(dropCustomerDetailsTable)) {
            preparedStatement.execute();
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(dropCustomerTable)) {
            preparedStatement.execute();
        }
    }
}



