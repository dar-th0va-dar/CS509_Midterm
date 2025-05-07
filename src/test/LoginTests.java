import org.junit.Test;
import src.interfaces.ILogin;
import src.interfaces.IUser;
import src.ui.Admin;
import src.ui.Customer;
import src.ui.Login;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;

public class LoginTests {
    @Test
    public void testCustomerValidLogin() {
        String string = "Adnan123\n12345\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(string.getBytes());
        System.setIn(inputStream);

        IUser expected = new Customer(2, "Adnan123", 12345, "Adnan", 0.0, "Active");
        ILogin login = new Login();
        IUser actual = login.login();

        assertEquals(expected, actual);
    }

    @Test
    public void testAdminValidLogin() {
        String string = "admin\n12345\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(string.getBytes());
        System.setIn(inputStream);

        IUser expected = Admin.getInstance();
        ILogin login = new Login();
        IUser actual = login.login();

        assertEquals(expected, actual);
    }

    @Test
    public void testInvalidLogin() {
        String string = "Adnan12\n12345\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(string.getBytes());
        System.setIn(inputStream);

        ILogin login = new Login();
        IUser actual = login.login();

        assertEquals(null, actual);
    }
}
