package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authenticate {
    public static IUser authenticateLogin(String login, int pin) throws SQLException {
        if (pin < 10000 || pin > 99999) {
            return null;
        }

        Connection database = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet user = null;

        String sql = "SELECT * FROM users WHERE login = ? AND pin = ?";

        assert database != null;
        preparedStatement = database.prepareStatement(sql);
        preparedStatement.setString(1, login);
        preparedStatement.setInt(2, pin);

        user = preparedStatement.executeQuery();

        if (user.next()) {
            if (user.getString("role").equals("customer")) {
                return new Customer(user.getInt("id"),
                        user.getString("login"),
                        user.getInt("pin"),
                        user.getString("holder"),
                        user.getDouble("balance"),
                        user.getString("status"));
            } else if (user.getString("role").equals("admin")) {
                return new Admin();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
