package Systems.DatabaseSystem;
import java.sql.*;

public class DatabaseSystem {
    Connection connection;
    Statement statement;
    public DbController() {
        super();
        //  TODO: get the server to create the database, build methods to execute certain statements/queries, and eventually CLOSE THE CONNECTION/STATEMENT!
        try {
            connection = DriverManager.getConnection("postgres://yuutancj:kJ_A46L2lp9K555CdLZOePOG4IEE8ixa@otto.db.elephantsql.com:5432/yuutancj", "yuutancj", "kJ_A46L2lp9K555CdLZOePOG4IEE8ixa");
            createDatabase();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void createDatabase() throws SQLException {
        String sql_stmt = "CREATE DATABASE IF NOT EXISTS `RPMS_db`;";
        statement = connection.createStatement();
        statement.executeUpdate(sql_stmt);
        System.out.println("RPMS_db has successfully been created");
    }
}
