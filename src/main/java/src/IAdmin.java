package src;

import java.sql.SQLException;

public interface IAdmin extends IUser{

    public void createNewAccount();
    public void deleteExistingAccount();
    public void updateAccountInfo();
    public void searchForAccount();
    public void exit();
}
