package Systems.DatabaseSystem;
import java.sql.*;

public class DatabaseSystem {
    Connection connection;
    Statement statement;
    public DatabaseSystem() {
        super();
        //  TODO: get the server to create the database, build methods to execute certain statements/queries, and eventually CLOSE THE CONNECTION/STATEMENT!
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://otto.db.elephantsql.com:5432/yuutancj", "yuutancj", "kJ_A46L2lp9K555CdLZOePOG4IEE8ixa");
            createDatabase();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void createDatabase() throws SQLException {
        System.out.println("Datbase \"yuutancj\" has successfully been created");
    }
}
