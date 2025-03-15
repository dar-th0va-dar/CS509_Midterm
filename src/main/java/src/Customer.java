package src;

import java.sql.*;
import java.util.Date;

public class Customer implements ICustomer {
    private int id;
    private String login;
    private int pin;
    private String name;
    private int balance;
    private String status;

    private Date date;

    public Customer() {

    }

    public Customer(String login) throws SQLException {
        this.login = login;
        date.getDate();

        Connection database = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM users WHERE login = ?";

        assert database != null;
        preparedStatement = database.prepareStatement(sql);
        preparedStatement.setString(1, login);

        resultSet = preparedStatement.executeQuery();

        id = resultSet.getInt("id");
        balance = resultSet.getInt("balance");
    }

    public String getName() {
        return name;
    }

    public static void addCustomer(String login, int pin, String name, int balance, String status) {
        // implement, needs to add to database
    }

    @Override
    public void withdrawCash(int amount) {
        if (amount < balance) {
            balance -= amount;
            System.out.println("Cash Successfully Withdrawn");
            System.out.println("Account #" + id);
            System.out.println("Date: " + date);
            System.out.println("Withdrawn: " + amount);
            System.out.println("Balance: " + balance);
        }
    }

    @Override
    public void depositCash(int amount) {
        System.out.println("Cash Deposited Successfully");
        System.out.println("Account #" + id);
        System.out.println("Date: " + date);
        System.out.println("Withdrawn: " + amount);
        System.out.println("Balance: " + balance);
    }

    @Override
    public void displayBalance() {
        System.out.println("Account #" + id);
        System.out.println("Date: " + date);
        System.out.println("Balance: " + balance);
    }

    @Override
    public void exit() {
        System.out.println("Thank you for using this ATM");
        System.exit(0);
    }

    @Override
    public String toString() {
        return "Account #" + id + "\n" +
                "Holder: " + name + "\n" +
                "Balance: " + balance + "\n" +
                "Status: " + status + "\n" +
                "Login: " + login + "\n" +
                "Pin Code: " + pin;
    }
}
