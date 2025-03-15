package org.example;

public interface IAdmin {

    public void createNewAccount();
    public void deleteExistingAccount(int accountNum);
    public void updateAccountInfo(int accountNum);
    public void searchForAccount(int accountNum);
    public void exit();
}
