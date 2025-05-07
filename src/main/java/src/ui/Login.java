package src.ui;

import com.google.inject.Inject;
import src.dal.DatabaseConnection;
import src.interfaces.ILogin;
import src.interfaces.IUser;

import java.util.Scanner;

public class Login implements ILogin {
    private static String login;

    @Inject
    public Login() {}

    /**
     * Checks the login information of the user
     * @return IUser of a Customer or Admin, depending on the account associated with the login information
     */
    public IUser login() {
        final Scanner s = new Scanner(System.in);

        System.out.println("Hello! Please login below");
        System.out.print("Login: ");

        if (s.hasNextLine()) {
            login = s.nextLine();
        }

        System.out.print("Pin: ");
        int pin = s.nextInt();

        System.out.println();
        if (pin < 10000 || pin > 99999) {
            return null;
        }

        return DatabaseConnection.findUserDatabase(login, pin);
    }
}
