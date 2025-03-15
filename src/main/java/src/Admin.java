package src;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin implements IAdmin {

    public Admin() {

    }

    @Override
    public void createNewAccount() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the account information");

        System.out.print("Login: ");
        String login = s.nextLine();

        System.out.print("Pin Code: ");
        int pin = s.nextInt();

        System.out.print("Holder's Name: ");
        String name = s.nextLine();

        System.out.print("Starting Balance: ");
        int balance = s.nextInt();

        System.out.print("Status: ");
        String status = s.nextLine();

        Customer.addCustomer(login, pin, name, balance, status);
    }

    @Override
    public void deleteExistingAccount() {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter the account number to which you want to delete: ");
        int id = s.nextInt();

        System.out.println();
        System.out.println("You wish to delete the account held by " + DatabaseConnection.findCustomer(id).getName() +
                ". If this information is correct, please re-enter the account number: ");
        int check = s.nextInt();

        if (id == check) {
            System.out.println("Account Deleted Successfully");
        } else {
            System.out.println("That is not the correct account, please try again");
        }
    }

    @Override
    public void updateAccountInfo() {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter Account number: ");
        int id = s.nextInt();

        System.out.println();
        System.out.println("The account information is:");
        DatabaseConnection.findCustomer(id).toString();
    }

    @Override
    public void searchForAccount() {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter Account Number: ");
        int id = s.nextInt();

        System.out.println();
        System.out.println("The account information is: ");
        DatabaseConnection.findCustomer(id).toString();
    }

    @Override
    public void exit() {

    }
}
