import org.junit.After;
import org.junit.Test;
import src.ui.Customer;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class CustomerTests {
    @After
    public void reset() {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @Test
    public void testDepositCash() {
        final Customer customer = new Customer(2, "Adnan123", 12345, "Adnan", 0.0, "Active");

        final String input = "300\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        customer.depositCash();

        final String output = outputStream.toString().trim();

        assertTrue(output.contains("Cash Deposited Successfully"));
    }

    @Test
    public void testValidWithdrawCash() {
        final Customer customer = new Customer(2, "Adnan123", 12345, "Adnan", 300.0, "Active");

        final String input = "300\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        customer.withdrawCash();

        final String output = outputStream.toString().trim();

        assertTrue(output.contains("Cash Successfully Withdrawn"));
    }

    @Test
    public void testInvalidWithdrawCash() {
        final Customer customer = new Customer(2, "Adnan123", 12345, "Adnan", 300.0, "Active");

        final String input = "400\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        customer.withdrawCash();

        final String output = outputStream.toString().trim();

        assertTrue(output.contains("Cash withdraw unsuccessful, insufficient balance"));
    }

    @Test
    public void testDisplayBalance() {
        final Customer customer = new Customer(2, "Adnan123", 12345, "Adnan", 300.0, "Active");

        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        customer.displayBalance();

        final String output = outputStream.toString().trim();

        assertTrue(output.contains("Account #2"));
        assertTrue(output.contains("Balance: 300.0"));
    }
}
