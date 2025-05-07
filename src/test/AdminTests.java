import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import src.dal.DatabaseConnection;
import src.interfaces.ICustomer;
import src.ui.Admin;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AdminTests {
    @Mock
    private MockedStatic<DatabaseConnection> mockedDatabase;
    Admin admin;
    ICustomer mockCustomer;

    @Before
    public void setUp() {
        mockedDatabase = mockStatic(DatabaseConnection.class);
        admin = Admin.getInstance();
        mockCustomer = mock(ICustomer.class);
    }

    @After
    public void reset() {
        mockedDatabase.close();
    }

    @Test
    public void testInstance() {
        assertEquals(Admin.getInstance(), admin);
    }

    @Test
    public void testCreateNewAccount() {
        String input = "test\n54321\ntest\n1000.5\nInactive";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        mockedDatabase.when(() -> DatabaseConnection.addCustomerToDatabase("test", 54321, "test", 1000.5, "Inactive"))
                .thenAnswer(invocation -> null);

        mockedDatabase.when(mockCustomer::getId).thenReturn(0);

        mockedDatabase.when(() -> DatabaseConnection.findUserDatabase("test", 54321)).thenReturn(mockCustomer);

        Admin.getInstance().createNewAccount();

        String output = outputStream.toString().trim();

        mockedDatabase.verify(() -> DatabaseConnection.addCustomerToDatabase("test", 54321, "test", 1000.5, "Inactive"), times(1));
        mockedDatabase.verify(() -> DatabaseConnection.findUserDatabase("test", 54321), times(1));
        assertTrue(output.contains("Account Successfully Created – the account number assigned is: "));
    }

    @Test
    public void testDeleteExistingAccount() {
        String input = "0\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        when(mockCustomer.getId()).thenReturn(0);
        when(mockCustomer.getName()).thenReturn("Test");

        mockedDatabase.when(() -> DatabaseConnection.findUserDatabase(0)).thenReturn(mockCustomer);

        mockedDatabase.when(() -> DatabaseConnection.deleteCustomerFromDatabase(0)).thenAnswer(inv -> null);

        Admin.getInstance().deleteExistingAccount();

        String output = outputStream.toString().trim();

        mockedDatabase.verify(() -> DatabaseConnection.deleteCustomerFromDatabase(0), times(1));
        mockedDatabase.verify(() -> DatabaseConnection.findUserDatabase(0), times(1));
        assertTrue(output.contains("If this information is correct, please re-enter the account number:"));
        assertFalse(output.contains("That is not the correct account, please try again"));
        assertFalse(output.contains("That is not an ID that is in the database"));
    }

    @Test
    public void testUpdateName() {
        String input = "1\n1\nnewHolder\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        mockedDatabase.when(() -> DatabaseConnection.findUserDatabase(1)).thenReturn(mockCustomer);
        mockedDatabase.when(() -> DatabaseConnection.updateStringDatabase(1, "holder", "newHolder")).thenReturn(true);

        Admin.getInstance().updateAccountInfo();

        String output = outputStream.toString().trim();

        mockedDatabase.verify(() -> DatabaseConnection.updateStringDatabase(1, "holder", "newHolder"), times(1));
        assertTrue(output.contains("Enter the new Holder: "));
        assertFalse(output.contains("That is not a valid option, please try again"));
    }

    @Test
    public void testUpdateStatus() {
        String input = "1\n2\ntest\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        mockedDatabase.when(() -> DatabaseConnection.findUserDatabase(1)).thenReturn(mockCustomer);
        mockedDatabase.when(() -> DatabaseConnection.updateStringDatabase(1, "status", "test")).thenReturn(true);

        Admin.getInstance().updateAccountInfo();

        String output = outputStream.toString().trim();

        mockedDatabase.verify(() -> DatabaseConnection.updateStringDatabase(1, "status", "test"), times(1));
        assertTrue(output.contains("Enter the new Status: "));
        assertFalse(output.contains("That is not a valid option, please try again"));
    }

    @Test
    public void testUpdateLogin() {
        String input = "1\n3\nnewlogin\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        mockedDatabase.when(() -> DatabaseConnection.findUserDatabase(1)).thenReturn(mockCustomer);
        mockedDatabase.when(() -> DatabaseConnection.updateStringDatabase(1, "login", "newlogin")).thenReturn(true);

        Admin.getInstance().updateAccountInfo();

        String output = outputStream.toString().trim();

        mockedDatabase.verify(() -> DatabaseConnection.updateStringDatabase(1, "login", "newlogin"), times(1));
        assertTrue(output.contains("Enter the new Login: "));
        assertFalse(output.contains("That is not a valid option, please try again"));
    }

    @Test
    public void testUpdatePin() {
        String input = "1\n4\n12345\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        mockedDatabase.when(() -> DatabaseConnection.findUserDatabase(1)).thenReturn(mockCustomer);
        mockedDatabase.when(() -> DatabaseConnection.updateIntDatabase(1, "pin", 12345)).thenReturn(true);

        Admin.getInstance().updateAccountInfo();

        String output = outputStream.toString().trim();

        mockedDatabase.verify(() -> DatabaseConnection.updateIntDatabase(1, "pin", 12345), times(1));
        assertTrue(output.contains("Enter the new Pin: "));
        assertFalse(output.contains("That is not a valid option, please try again"));
    }

    @Test
    public void testSearchForAccount() {
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        mockedDatabase.when(() -> DatabaseConnection.findUserDatabase(1)).thenReturn(mockCustomer);

        Admin.getInstance().searchForAccount();

        mockedDatabase.verify(() -> DatabaseConnection.findUserDatabase(1), times(1));

        String output = outputStream.toString().trim();

        assertTrue(output.contains("The account information is:"));
        assertFalse(output.contains("Could not find user"));
    }
}
