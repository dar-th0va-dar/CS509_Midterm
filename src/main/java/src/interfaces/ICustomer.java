package src.interfaces;

public interface ICustomer extends IUser {

    void withdrawCash();
    void depositCash();
    void displayBalance();

    int getId();
    String getName();
}
