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
        Customer customer = new Customer(2, "Adnan123", 12345, "Adnan", 0.0, "Active");

        String input = "300\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        customer.depositCash();

        String output = outputStream.toString().trim();

        assertTrue(output.contains("Cash Deposited Successfully"));
    }

    @Test
    public void testValidWithdrawCash() {
        Customer customer = new Customer(2, "Adnan123", 12345, "Adnan", 300.0, "Active");

        String input = "300\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        customer.withdrawCash();

        String output = outputStream.toString().trim();

        assertTrue(output.contains("Cash Successfully Withdrawn"));
    }

    @Test
    public void testInvalidWithdrawCash() {
        Customer customer = new Customer(2, "Adnan123", 12345, "Adnan", 300.0, "Active");

        String input = "400\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        customer.withdrawCash();

        String output = outputStream.toString().trim();

        assertTrue(output.contains("Cash withdraw unsuccessful, insufficient balance"));
    }

    @Test
    public void testDisplayBalance() {
        Customer customer = new Customer(2, "Adnan123", 12345, "Adnan", 300.0, "Active");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        customer.displayBalance();

        String output = outputStream.toString().trim();

        assertTrue(output.contains("Account #2"));
        assertTrue(output.contains("Balance: 300.0"));
    }
}
