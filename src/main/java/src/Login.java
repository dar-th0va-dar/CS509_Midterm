package src;

import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    private static String login;
    private static int pin;

    public static IUser login() throws SQLException {
        Scanner s = new Scanner(System.in);

        System.out.println("Hello! Please login below");
        System.out.print("Login: ");

        if (s.hasNextLine()) {
            login = s.nextLine();
        }

        System.out.print("Pin: ");
        pin = s.nextInt();

        System.out.println();
        if (pin < 10000 || pin > 99999) {
            return null;
        }

        return DatabaseConnection.findUserDatabase(login, pin);
    }
}
