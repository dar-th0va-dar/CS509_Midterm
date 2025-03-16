package src;

import java.sql.SQLException;

public interface IAdmin extends IUser{
    void createNewAccount();
    void deleteExistingAccount();
    void updateAccountInfo();
    void searchForAccount();
}
