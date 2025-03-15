package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
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
        IUser user = Authenticate.authenticateLogin(login, pin);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }
}
