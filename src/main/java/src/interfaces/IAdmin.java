package src.interfaces;

public interface IAdmin extends IUser{
    void createNewAccount();
    void deleteExistingAccount();
    void updateAccountInfo();
    void searchForAccount();
}
