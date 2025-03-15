package src;

public interface ICustomer extends IUser {

    public void withdrawCash(double amount);
    public void depositCash(double amount);
    public void displayBalance();
    public void exit();

    String getName();
}
