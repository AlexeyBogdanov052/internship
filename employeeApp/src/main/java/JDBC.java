import java.sql.*;

public class JDBC {
    public static void Task() {
        try {
            Connection connection = null;
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:58925/staff", "postgres", "postres");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("INSERT INTO scheme_rights (operation_name) values(...)");

            result.close();
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
