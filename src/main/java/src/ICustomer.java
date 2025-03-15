package src;

public interface ICustomer extends IUser {

    public void withdrawCash(int amount);
    public void depositCash(int amount);
    public void displayBalance();
    public void exit();

    String getName();
}
