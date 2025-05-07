package src.ui;

import com.google.inject.Inject;
import src.interfaces.*;

import java.util.Scanner;

public class Run implements IRun {
    final ILogin login;

    @Inject
    public Run(final ILogin login) {
        this.login = login;
    }

    /**
     * Runs the ATM using information from the terminal
     */
    @Override
    public void runATM() {
        final Scanner s = new Scanner(System.in);
        IUser user = null;

        while (user == null) {
            user = login.login();

            if (user instanceof ICustomer) {
                System.out.println("Welcome " + ((ICustomer) user).getName() + "!");

                while (true) {
                    System.out.println();
                    System.out.println("1----Withdraw Cash");
                    System.out.println("2----Deposit Cash");
                    System.out.println("3----Display Balance");
                    System.out.println("4----Exit");
                    System.out.print("What would you like to do? (Enter the number): ");

                    final int menu = s.nextInt();
                    System.out.println();
                    switch (menu) {
                        case 1:
                            ((ICustomer) user).withdrawCash();
                            break;
                        case 2:
                            ((ICustomer) user).depositCash();
                            break;
                        case 3:
                            ((ICustomer) user).displayBalance();
                            break;
                        case 4:
                            user.exit();
                            break;
                        default:
                            System.out.println("That is not a valid option, please try again");
                    }
                }
            } else if (user instanceof IAdmin) {
                System.out.println("Welcome Admin");

                while (true) {
                    System.out.println();
                    System.out.println("1----Create New Account");
                    System.out.println("2----Delete Existing Account");
                    System.out.println("3----Update Account Information");
                    System.out.println("4----Search for Account");
                    System.out.println("5----Exit");
                    System.out.print("What would you like to do? (Enter the number): ");

                    final int menu = s.nextInt();
                    System.out.println();
                    switch (menu) {
                        case 1:
                            ((IAdmin) user).createNewAccount();
                            break;
                        case 2:
                            ((IAdmin) user).deleteExistingAccount();
                            break;
                        case 3:
                            ((IAdmin) user).updateAccountInfo();
                            break;
                        case 4:
                            ((IAdmin) user).searchForAccount();
                            break;
                        case 5:
                            user.exit();
                            break;
                        default:
                            System.out.println("That is not a valid option, please try again");
                    }
                }
            } else {
                System.out.println("That was an incorrect login, please try again");
            }
        }
    }
}
