package src;

import java.sql.*;
import java.util.Date;

public class Customer implements ICustomer {
    private String login;
    private int accountNum;
    private int balance;
    private Date date;

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

        accountNum = resultSet.getInt("id");
        balance = resultSet.getInt("balance");
    }

    @Override
    public void withdrawCash(int amount) {
        if (amount < balance) {
            balance -= amount;
            System.out.println("Cash Successfully Withdrawn");
            System.out.println("Account #" + accountNum);
            System.out.println("Date: " + date);
            System.out.println("Withdrawn: " + amount);
            System.out.println("Balance: " + balance);
        }
    }

    @Override
    public void depositCash(int amount) {
        System.out.println("Cash Deposited Successfully");
        System.out.println("Account #" + accountNum);
        System.out.println("Date: " + date);
        System.out.println("Withdrawn: " + amount);
        System.out.println("Balance: " + balance);
    }

    @Override
    public void displayBalance() {
        System.out.println("Account #" + accountNum);
        System.out.println("Date: " + date);
        System.out.println("Balance: " + balance);
    }

    @Override
    public void exit() {
        System.out.println("Thank you for using this ATM");
        System.exit(0);
    }
}
