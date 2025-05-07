package src.ui;

import src.dal.DatabaseConnection;
import src.interfaces.ICustomer;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class Customer implements ICustomer {
    private final int id;
    private String login;
    private int pin;
    private String name;
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

        System.out.print("Enter the withdrawal amount: ");
        double amount = s.nextDouble();

        if (amount <= balance) {
            balance -= amount;

            boolean update = DatabaseConnection.updateDoubleDatabase(id, "balance", balance);
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

    @Override
    public void depositCash() {
        Scanner s = new Scanner(System.in);
        date = LocalDate.now();

        System.out.print("Enter the cash amount to deposit: ");
        double amount = s.nextDouble();
        balance += amount;

        boolean update = DatabaseConnection.updateDoubleDatabase(id, "balance", balance);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(login, customer.login) &&
                Objects.equals(pin, customer.pin) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(balance, customer.balance) &&
                Objects.equals(status, customer.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
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
