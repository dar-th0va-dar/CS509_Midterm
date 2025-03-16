package src;

import com.google.inject.Inject;

import java.util.Scanner;

public class Login implements ILogin {
    private static String login;
    private static int pin;

    @Inject
    public Login() {}

    public IUser login() {
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
