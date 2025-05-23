package src.ui;

import src.dal.DatabaseConnection;
import src.interfaces.IAdmin;
import src.interfaces.ICustomer;
import src.interfaces.IUser;

import java.util.Scanner;

public class Admin implements IAdmin {
    private static final Admin INSTANCE = new Admin();

    private Admin() {}

    public static Admin getInstance() {
        return INSTANCE;
    }

    /**
     * Create a new customer account using information from the terminal
     */
    @Override
    public void createNewAccount() {
        final Scanner s = new Scanner(System.in);
        System.out.println("Please enter the account information");

        System.out.print("Login: ");
        final String login = s.nextLine();

        System.out.print("Pin Code: ");
        final int pin = s.nextInt();
        s.nextLine();

        System.out.print("Holder's Name: ");
        final String name = s.nextLine();

        System.out.print("Starting Balance: ");
        final double balance = s.nextDouble();
        s.nextLine();

        System.out.print("Status: ");
        final String status = s.nextLine();

        DatabaseConnection.addCustomerToDatabase(login, pin, name, balance, status);

        final ICustomer customer = (ICustomer) DatabaseConnection.findUserDatabase(login, pin);
        System.out.println("Account Successfully Created – the account number assigned is: " +
                customer.getId());
    }

    /**
     * Deletes a customer account using information from the terminal
     */
    @Override
    public void deleteExistingAccount() {
        final Scanner s = new Scanner(System.in);

        System.out.print("Enter the account number to which you want to delete: ");
        final int id = s.nextInt();

        System.out.println();
        final ICustomer customer = (ICustomer) DatabaseConnection.findUserDatabase(id);
        if (customer != null) {
            System.out.print("You wish to delete the account held by " + customer.getName() +
                    ". If this information is correct, please re-enter the account number: ");
            final int check = s.nextInt();

            if (id == check) {
                DatabaseConnection.deleteCustomerFromDatabase(id);
            } else {
                System.out.println("That is not the correct account, please try again");
            }
        } else {
            System.out.println("That is not an ID that is in the database");
        }
    }

    /**
     * Updates a customer account using information from the terminal
     */
    @Override
    public void updateAccountInfo() {
        final Scanner s = new Scanner(System.in);

        System.out.print("Enter Account number: ");
        final int id = s.nextInt();

        while (true) {
            System.out.println();
            System.out.println("The account information is:");
            System.out.println(DatabaseConnection.findUserDatabase(id).toString());

            System.out.println();
            System.out.println("1----Holder");
            System.out.println("2----Status");
            System.out.println("3----Login");
            System.out.println("4----Pin");
            System.out.println("5----Exit");
            System.out.print("What would you like to change? (Enter the number): ");

            final int menu = s.nextInt();
            s.nextLine();

            switch (menu) {
                case 1:
                    System.out.println();
                    System.out.print("Enter the new Holder: ");
                    DatabaseConnection.updateStringDatabase(id, "holder", s.nextLine());
                    break;
                case 2:
                    System.out.println();
                    System.out.print("Enter the new Status: ");
                    DatabaseConnection.updateStringDatabase(id, "status", s.nextLine());
                    break;
                case 3:
                    System.out.println();
                    System.out.print("Enter the new Login: ");
                    DatabaseConnection.updateStringDatabase(id, "login", s.nextLine());
                    break;
                case 4:
                    System.out.println();
                    System.out.print("Enter the new Pin: ");
                    DatabaseConnection.updateIntDatabase(id, "pin", s.nextInt());
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("That is not a valid option, please try again");
            }
        }
    }

    /**
     * Searches for a customer account using information from the terminal
     */
    @Override
    public void searchForAccount() {
        final Scanner s = new Scanner(System.in);

        System.out.print("Enter Account Number: ");
        final int id = s.nextInt();

        System.out.println();
        final IUser user = DatabaseConnection.findUserDatabase(id);

        if (user != null) {
            System.out.println("The account information is: ");
            System.out.println(user);
        } else {
            System.out.println("Could not find user");
        }
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
        return this == obj;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }
}
