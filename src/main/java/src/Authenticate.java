package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authenticate {
    public static boolean authenticateLogin(String login, int pin) throws SQLException {
        if (pin < 10000 || pin > 99999) {
            return false;
        }

        Connection database = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM users WHERE login = ? AND pin = ?";

        assert database != null;
        preparedStatement = database.prepareStatement(sql);
        preparedStatement.setString(1, login);
        preparedStatement.setInt(2, pin);

        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }
}
