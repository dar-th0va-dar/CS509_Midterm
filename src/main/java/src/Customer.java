package src;

import java.time.LocalDate;
import java.util.Scanner;

public class Customer implements ICustomer {
    private final int id;
    private final String login;
    private final int pin;
    private final String name;
    private double balance;
    private String status;

    private LocalDate date;

    public Customer(int id, String login, int pin, String name, double balance, String status) {
        this.id = id;
        this.login = login;
        this.pin = pin;
        this.name = name;
        this.balance = balance;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public void withdrawCash() {
        Scanner s = new Scanner(System.in);
        date = LocalDate.now();

        System.out.println("Enter the withdrawal amount: ");
        double amount = s.nextDouble();

        if (amount < balance) {
            balance -= amount;
            System.out.println("Cash Successfully Withdrawn");
            System.out.println("Account #" + id);
            System.out.println("Date: " + date);
            System.out.println("Withdrawn: " + amount);
            System.out.println("Balance: " + balance);

            DatabaseConnection.updateBalanceDatabase("balance", balance);
        } else {
            System.out.println("Cash Withdraw Unsuccessful, Insufficient Balance");
        }
    }

    @Override
    public void depositCash() {
        Scanner s = new Scanner(System.in);
        date = LocalDate.now();

        System.out.println("Enter the cash amount to deposit: ");
        double amount = s.nextDouble();
        balance += amount;

        System.out.println("Cash Deposited Successfully");
        System.out.println("Account #" + id);
        System.out.println("Date: " + date);
        System.out.println("Withdrawn: " + amount);
        System.out.println("Balance: " + balance);

        DatabaseConnection.updateBalanceDatabase("balance", balance);
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
        System.out.println("Thank you for using this ATM!");
        System.exit(0);
    }

    @Override
    public String toString() {
        return "Account #" + id + "\n" +
                "Holder: " + name + "\n" +
                "Balance: " + balance + "\n" +
                "Status: " + status.substring(0, 1).toUpperCase() + status.substring(1) + "\n" +
                "Login: " + login + "\n" +
                "Pin Code: " + pin;
    }
}
