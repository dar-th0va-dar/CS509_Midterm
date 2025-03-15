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
    private static String role;

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
            Connection database = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String sql = "SELECT * FROM users WHERE role = ?";

            assert database != null;
            preparedStatement = database.prepareStatement(sql);
            preparedStatement.setString(1, role);

            resultSet = preparedStatement.executeQuery();

            if (Objects.equals(resultSet.getString("role"), "customer")) {
                System.out.println("Welcome Customer!");
                new Customer(login);
            } else if (Objects.equals(resultSet.getString("role"), "admin")) {
                new Admin();
            }
        }
    }
}
