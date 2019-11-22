package Systems;
import java.sql.*;
import java.util.Hashtable;

import Entity.Account;
import Entity.LandlordAccount;
import Entity.ManagerAccount;
import Entity.Name;
import Entity.RegisteredRenterAccount;

public class DatabaseSystem {
    Connection connection;
    Statement statement;
    ResultSet rs;
    public DatabaseSystem() {
        super();
        //  TODO: get the server to create the database, build methods to execute certain statements/queries, and eventually CLOSE THE CONNECTION/STATEMENT!
        try {
            connection = DriverManager.getConnection("jdbc:mysql://remotemysql.com:5432/bhodcTO1R2", "bhodcTO1R2", "NFeETF8mpF");
            System.out.println("Database has successfully been connected.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public String authenticate(String email, String password) {
        try {
            String statementStr = "SELECT * FROM ACCOUNT WHERE email = `email` AND password = `password`";
            statement = connection.createStatement();
            rs = statement.executeQuery(statementStr);
            return rs.getInt("id") + "-" + rs.getString("type");
        } catch(Exception e) {
            return null;
        }
    }

    public LandlordAccount getLandlordAccount(String email, String password) {
        try {
            String statementStr = "SELECT * FROM ACCOUNT WHERE email = `email` AND password = `password`";
            statement = connection.createStatement();
            rs = statement.executeQuery(statementStr);
            return new LandlordAccount (new Name(rs.getString("fname"), rs.getString("lname")),
                    rs.getInt("id"), rs.getString("email"));
        } catch(Exception e) {
            return null;
        }
    }

    public ManagerAccount getManagerAccount(String email, String password) {
        try {
            String statementStr = "SELECT * FROM ACCOUNT WHERE email = `email` AND password = `password`";
            statement = connection.createStatement();
            rs = statement.executeQuery(statementStr);
            return new ManagerAccount(new Name(rs.getString("fname"), rs.getString("lname")),
                    rs.getInt("id"), rs.getString("email"));
        } catch(Exception e) {
            return null;
        }
    }

    public RegisteredRenterAccount getRenterAccount(String email, String password) {
        try {
            String statementStr = "SELECT * FROM ACCOUNT WHERE email = `email` AND password = `password`";
            statement = connection.createStatement();
            rs = statement.executeQuery(statementStr);
            return new RegisteredRenterAccount (new Name(rs.getString("fname"), rs.getString("lname")),
                    rs.getInt("id"), rs.getString("email"));
        } catch(Exception e) {
            return null;
        }
    }
}
