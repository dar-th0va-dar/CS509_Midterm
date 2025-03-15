package src;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner s = new Scanner(System.in);
        IUser user = Login.login();

        if (user instanceof ICustomer) {
            System.out.println("Welcome " + ((ICustomer) user).getName() + "!");

            while (true) {
                System.out.println();
                System.out.println("1----Withdraw Cash");
                System.out.println("2----Deposit Cash");
                System.out.println("3----Display Balance");
                System.out.println("4----Exit");
                System.out.print("What would you like to do? (Enter the number): ");

                int menu = s.nextInt();
                switch (menu) {
                    case 1:
                        System.out.println("Enter the withdrawal amount: ");

                        ((ICustomer) user).withdrawCash(s.nextDouble());
                        break;
                    case 2:
                        System.out.println("Enter the cash amount to deposit: ");

                        ((ICustomer) user).depositCash(s.nextDouble());
                        break;
                    case 3:
                        ((ICustomer) user).displayBalance();
                        break;
                    case 4:
                        System.out.println("Thank you for using this ATM!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("That is not a valid option, please try again");
                }
            }

        } else if (user instanceof IAdmin) {
            System.out.println("Welcome Admin");
        } else {
            System.out.println("That was an incorrect login, please try again");
            System.exit(0);
        }
    }
}
