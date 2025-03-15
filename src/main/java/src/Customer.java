package src;

import java.sql.*;
import java.time.LocalDate;

public class Customer implements ICustomer {
    private int id;
    private String login;
    private int pin;
    private String name;
    private double balance;
    private String status;

    private LocalDate date;

//    public Customer(String login) throws SQLException {
//        this.login = login;
//        date = LocalDate.now();
//
//        Connection database = DatabaseConnection.getConnection();
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//        String sql = "SELECT * FROM users WHERE login = ?";
//
//        assert database != null;
//        preparedStatement = database.prepareStatement(sql);
//        preparedStatement.setString(1, login);
//
//        resultSet = preparedStatement.executeQuery();
//
//        id = resultSet.getInt("id");
//        balance = resultSet.getInt("balance");
//    }

    public Customer(int id, String login, int pin, String name, double balance, String status) {
        this.id = id;
        this.login = login;
        this.pin = pin;
        this.name = name;
        this.balance = balance;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public static void addCustomerToDatabase(String login, int pin, String name, int balance, String status) {
        // implement, needs to add to database
    }

    @Override
    public void withdrawCash(double amount) {
        date = LocalDate.now();

        if (amount < balance) {
            balance -= amount;
            System.out.println("Cash Successfully Withdrawn");
            System.out.println("Account #" + id);
            System.out.println("Date: " + date);
            System.out.println("Withdrawn: " + amount);
            System.out.println("Balance: " + balance);

            // update database
        } else {
            System.out.println("Cash Withdraw Unsuccessful, Insufficient Balance");
        }
    }

    @Override
    public void depositCash(double amount) {
        date = LocalDate.now();
        balance += amount;

        System.out.println("Cash Deposited Successfully");
        System.out.println("Account #" + id);
        System.out.println("Date: " + date);
        System.out.println("Withdrawn: " + amount);
        System.out.println("Balance: " + balance);

        // update database
    }

    @Override
    public void displayBalance() {
        date = LocalDate.now();

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
