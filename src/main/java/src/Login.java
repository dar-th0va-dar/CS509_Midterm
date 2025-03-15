package src;

import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    private static String login;
    private static int pin;
    private String role;

    public Login() {

    }

    public static void login() throws SQLException {
        Scanner s = new Scanner(System.in);

        System.out.println("Hello! Please login below");
        System.out.print("Login: ");

        if (s.hasNextLine()) {
            login = s.nextLine();
        }

        System.out.print("Pin: ");
        pin = s.nextInt();

        System.out.println();
        if (!Authenticate.authenticateLogin(login, pin)) {
            System.out.println("That was an incorrect login, please try again");
            System.exit(0);
        } else {
            System.out.println("Welcome!");

            Customer user = new Customer();
        }
    }
}
