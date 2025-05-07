import org.junit.Test;
import src.interfaces.ILogin;
import src.interfaces.IUser;
import src.ui.Admin;
import src.ui.Customer;
import src.ui.Login;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LoginTests {
    @Test
    public void testCustomerValidLogin() {
        final String input = "Adnan123\n12345\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        final IUser expected = new Customer(2, "Adnan123", 12345, "Adnan", 0.0, "Active");
        final ILogin login = new Login();
        final IUser actual = login.login();

        assertEquals(expected, actual);
    }

    @Test
    public void testAdminValidLogin() {
        final String input = "admin\n12345\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        final IUser expected = Admin.getInstance();
        final ILogin login = new Login();
        final IUser actual = login.login();

        assertEquals(expected, actual);
    }

    @Test
    public void testInvalidLogin() {
        final String input = "Adnan12\n12345\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        final ILogin login = new Login();
        final IUser actual = login.login();

        assertNull(actual);
    }
}
