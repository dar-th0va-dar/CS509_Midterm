package src;

import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/user_management";
    private static final String USER = "root";  // Replace with your MySQL username
    private static final String PASSWORD = "Awesome19*";  // Replace with your MySQL password

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static IUser findUserDatabase(String login, int pin) throws SQLException {
        Connection database = DatabaseConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet user = null;

        String sql = "SELECT * FROM users WHERE login = ? AND pin = ?";

        assert database != null;
        statement = database.prepareStatement(sql);
        statement.setString(1, login);
        statement.setInt(2, pin);

        user = statement.executeQuery();

        if (user.next()) {
            if (user.getString("role").equals("customer")) {
                return new Customer(user.getInt("id"),
                        user.getString("login"),
                        user.getInt("pin"),
                        user.getString("holder"),
                        user.getDouble("balance"),
                        user.getString("status"));
            } else if (user.getString("role").equals("admin")) {
                return new Admin();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static IUser findUserDatabase(int id) {
        try (Connection database = DatabaseConnection.getConnection()) {
            PreparedStatement statement = null;
            ResultSet user = null;

            String sql = "SELECT * FROM users WHERE id = ?";

            statement = database.prepareStatement(sql);
            statement.setInt(1, id);

            user = statement.executeQuery();

            if (user.next()) {
                return new Customer(user.getInt("id"),
                        user.getString("login"),
                        user.getInt("pin"),
                        user.getString("holder"),
                        user.getDouble("balance"),
                        user.getString("status"));
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("That is not a user that is in the database");
            return null;
        }
    }

    public static void addCustomerToDatabase(String login, int pin, String name, double balance, String status) {
        String sql = "INSERT INTO users (login, pin, role, holder, balance, status) VALUES (?, ?, 'customer', ?, ?, ?)";

        try (Connection database = getConnection();
             PreparedStatement statement = database.prepareStatement(sql)) {

            statement.setString(1, login);
            statement.setInt(2, pin);
            statement.setString(3, name);
            statement.setDouble(4, balance);
            statement.setString(5, status);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer added successfully!");
            } else {
                System.out.println("Failed to add customer.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCustomerFromDatabase(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Account Deleted Successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateBalanceDatabase(String column, double amount) {

    }
}
