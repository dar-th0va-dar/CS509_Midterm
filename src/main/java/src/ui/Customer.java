package src.ui;

import src.dal.DatabaseConnection;
import src.interfaces.ICustomer;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class Customer implements ICustomer {
    private final int id;
    private final String login;
    private final int pin;
    private final String name;
    private double balance;
    private final String status;

    private LocalDate date;

    public Customer(final int id, final String login, final int pin, final String name, final double balance, final String status) {
        this.id = id;
        this.login = login;
        this.pin = pin;
        this.name = name;
        this.balance = balance;
        this.status = status;
    }

    /**
     * Gets the customer's database ID
     * @return int ID of the customer
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the customer
     * @return String name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * Withdraws cash from the customer's account using information from the terminal
     */
    @Override
    public void withdrawCash() {
        final Scanner s = new Scanner(System.in);
        date = LocalDate.now();

        System.out.print("Enter the withdrawal amount: ");
        final double amount = s.nextDouble();

        if (amount <= balance) {
            balance -= amount;

            final boolean update = DatabaseConnection.updateDoubleDatabase(id, "balance", balance);
            if (update) {
                System.out.println("Cash Successfully Withdrawn");
                System.out.println("Account #" + id);
                System.out.println("Date: " + date);
                System.out.println("Withdrawn: " + amount);
                System.out.println("Balance: " + balance);
            } else {
                System.out.println("Unsuccessful deposit");
            }
        } else {
            System.out.println("Cash withdraw unsuccessful, insufficient balance");
        }
    }

    /**
     * Deposits cash into the customer's account using information from the terminal
     */
    @Override
    public void depositCash() {
        final Scanner s = new Scanner(System.in);
        date = LocalDate.now();

        System.out.print("Enter the cash amount to deposit: ");
        final double amount = s.nextDouble();
        balance += amount;

        final boolean update = DatabaseConnection.updateDoubleDatabase(id, "balance", balance);
        if (update) {
            System.out.println("Cash Deposited Successfully");
            System.out.println("Account #" + id);
            System.out.println("Date: " + date);
            System.out.println("Deposited: " + amount);
            System.out.println("Balance: " + balance);
        } else {
            System.out.println("Unsuccessful deposit");
        }
    }

    /**
     * Displays the balance of the customer's account
     */
    @Override
    public void displayBalance() {
        date = LocalDate.now();

        System.out.println("Account #" + id);
        System.out.println("Date: " + date);
        System.out.println("Balance: " + balance);
    }

    /**
     * Exits the ATM
     */
    @Override
    public void exit() {
        System.out.println("Thank you for using this ATM!");
        System.exit(0);
    }

    /**
     * Checks if 2 objects are the same
     * @param obj Object to compare with
     * @return boolean if the objects are the same
     */
    @Override
    public boolean equals(final Object obj) {
        final Customer customer = (Customer) obj;
        return id == customer.id &&
                Objects.equals(login, customer.login) &&
                Objects.equals(pin, customer.pin) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(balance, customer.balance) &&
                Objects.equals(status, customer.status);
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
