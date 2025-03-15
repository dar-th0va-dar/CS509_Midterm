package src;

public interface ICustomer extends IUser {

    public void withdrawCash();
    public void depositCash();
    public void displayBalance();
    public void exit();

    String getName();
}
