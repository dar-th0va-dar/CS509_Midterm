package src.dal;

import src.ui.Admin;
import src.ui.Customer;
import src.interfaces.IUser;

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

    /**
     * Finds a user in the database based on the login and pin number
     * @param login identifying login name
     * @param pin identifying login pin
     * @return IUser of a Customer or Admin, depending on the account associated with the login information
     */
    public static IUser findUserDatabase(String login, int pin) {
        String sql = "SELECT * FROM users WHERE login = ? AND pin = ?";

        try (Connection database = DatabaseConnection.getConnection();
             PreparedStatement statement = database.prepareStatement(sql)) {

            statement.setString(1, login);
            statement.setInt(2, pin);

            ResultSet user = statement.executeQuery();
            if (user.next()) {
                if (user.getString("role").equals("customer")) {
                    return new Customer(user.getInt("id"),
                            user.getString("login"),
                            user.getInt("pin"),
                            user.getString("holder"),
                            user.getDouble("balance"),
                            user.getString("status"));
                } else if (user.getString("role").equals("admin")) {
                    return Admin.getInstance();
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Finds a user in the database based on the database ID
     * @param id auto-assigned ID from the database to find the customer
     * @return IUser of a Customer or Admin, depending on the account associated with the ID number
     */
    public static IUser findUserDatabase(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection database = DatabaseConnection.getConnection();
             PreparedStatement statement = database.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet user = statement.executeQuery();
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
            return null;
        }
    }

    /**
     * Adds a customer with the given information to the database
     * @param login identifying login name
     * @param pin identifying login pin
     * @param name name of the customer
     * @param balance starting balance of the customer
     * @param status current status of the account
     */
    public static void addCustomerToDatabase(String login, int pin, String name, double balance, String status) {
        String sql = "INSERT INTO users (login, pin, role, holder, balance, status) VALUES (?, ?, 'customer', ?, ?, ?)";

        try (Connection database = getConnection();
             PreparedStatement statement = database.prepareStatement(sql)) {

            statement.setString(1, login);
            statement.setInt(2, pin);
            statement.setString(3, name);
            statement.setDouble(4, balance);
            statement.setString(5, status);

            statement.executeUpdate();
            System.out.println("Customer added successfully!");
        } catch (Exception e) {
            System.out.println("Failed to add customer.");
        }
    }

    /**
     * Finds and deletes a user in the database based on the database ID
     * @param id auto-assigned ID from the database for the customer to delete
     */
    public static void deleteCustomerFromDatabase(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, id);

            statement.executeUpdate();
            System.out.println("Account Deleted Successfully");
        } catch (Exception e) {
            System.out.println("Failed to delete customer.");
        }
    }

    /**
     * Update an integer in the database
     * @param id ID number of the user to update
     * @param column column in the database to update
     * @param num the number to update to
     * @return boolean of if the update worked
     */
    public static boolean updateIntDatabase(int id, String column, int num) {
        String sql = "UPDATE users SET " + column + " = ? WHERE id = ?";

        try (Connection database = DatabaseConnection.getConnection();
             PreparedStatement statement = database.prepareStatement(sql)) {

            statement.setInt(1, num);
            statement.setInt(2, id);

            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Update an double in the database
     * @param id ID number of the user to update
     * @param column column in the database to update
     * @param num the number to update to
     * @return boolean of if the update worked
     */
    public static boolean updateDoubleDatabase(int id, String column, double num) {
        String sql = "UPDATE users SET " + column + " = ? WHERE id = ?";

        try (Connection database = DatabaseConnection.getConnection();
             PreparedStatement statement = database.prepareStatement(sql)) {

            statement.setDouble(1, num);
            statement.setInt(2, id);

            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Update a String in the database
     * @param id ID number of the user to update
     * @param column column in the database to update
     * @param string the string to update to
     * @return boolean of if the update worked
     */
    public static boolean updateStringDatabase(int id, String column, String string) {
        String sql = "UPDATE users SET " + column + " = ? WHERE id = ?";

        try (Connection database = DatabaseConnection.getConnection();
             PreparedStatement statement = database.prepareStatement(sql)) {

            statement.setString(1, string);
            statement.setInt(2, id);

            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
