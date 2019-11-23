package Systems;

import java.sql.*;
import java.util.ArrayList;

import Entity.*;

public class DatabaseSystem {
    Connection connection;
    Statement statement;
    PreparedStatement pStatement;
    ResultSet rs;

    public DatabaseSystem() {
        //  TODO: get the server to create the database, build methods to execute certain statements/queries, and eventually CLOSE THE CONNECTION/STATEMENT!
        try {
            connection = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/bhodcTO1R2", "bhodcTO1R2", "NFeETF8mpF");
            System.out.println("Database has successfully been connected.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public String authenticate(String email, String password) {
        try {
            rs = getAccount(email, password);
            return rs.getInt("id") + "-" + rs.getString("type");
        } catch (Exception e) {
            System.out.println("Error logging in user " + email);
            return null;
        }
    }

    public ArrayList<Listing> getListings(SearchCriteria sc) {
        try {
            String statementStr = "SELECT * FROM `Listing` AS L, `Property` AS P WHERE ";
            statementStr = updateSearchStatementCriteria(statementStr, sc);
            statement = connection.createStatement();
            rs = statement.executeQuery(statementStr);
            ArrayList<Listing> listings = new ArrayList<>();
            while (rs.next()) {
                listings.add(
                        new Listing(rs.getInt("landlordId"), new Property(
                                rs.getString("type"), rs.getInt("numBedrooms"), rs.getDouble("numBathrooms"), rs.getBoolean("isFurnished"),
                                rs.getString("quadrant"), new Address(
                                        rs.getString("street"), rs.getString("city"),
                                        rs.getString("country"), rs.getString("postalCode")),
                                rs.getInt("P.id")),
                            rs.getDouble("fee")));
            }
            return listings;
        } catch (SQLException e) {
            System.out.println("Failed to retrieve listings.");
            return null;
        }
    }

    public void insertSearchCriteria(SearchCriteria sc) {
        try {
            String statementStr = "INSERT INTO `SearchCriteria` (`quadrant`, `isFurnished`, `numOfBathrooms`, `numOfBedrooms`, `type`) values(?, ?, ?, ?, ?)";
            PreparedStatement pStmt = connection.prepareStatement(statementStr);
            pStmt.setString(1, sc.getQuadrant());
            pStmt.setBoolean(2, sc.isFurnished());
            pStmt.setDouble(3, sc.getNumOfBathrooms());
            pStmt.setInt(4, sc.getNumOfBedrooms());
            pStmt.setString(5, sc.getType());

            pStmt.executeUpdate();
            pStmt.close();
        } catch (SQLException e) {
            System.out.println("Error inserting into table.");
            e.printStackTrace();
            return;
        }
    }

    private ResultSet getAccount(String email, String password) throws SQLException {
        String statementStr = "SELECT * FROM `Account` WHERE `email`=? AND `password`=?";
        pStatement = connection.prepareStatement(statementStr);
        pStatement.setString(1, email);
        pStatement.setString(2, password);
        return pStatement.executeQuery();
    }

    public LandlordAccount getLandlordAccount(String email, String password) {
        try {
            rs = getAccount(email, password);

            return new LandlordAccount(new Name(rs.getString("fname"), rs.getString("lname")),
                    rs.getInt("id"), rs.getString("email"));
        } catch (Exception e) {
            System.out.println("Error getting user " + email);
            return null;
        }
    }

    public ManagerAccount getManagerAccount(String email, String password) {
        try {
            rs = getAccount(email, password);
            return new ManagerAccount(new Name(rs.getString("fname"), rs.getString("lname")),
                    rs.getInt("id"), rs.getString("email"));
        } catch (Exception e) {
            System.out.println("Error getting user " + email);
            return null;
        }
    }

    public RegisteredRenterAccount getRenterAccount(String email, String password) {
        try {
            rs = getAccount(email, password);
            return new RegisteredRenterAccount(new Name(rs.getString("fname"), rs.getString("lname")),
                    rs.getInt("id"), rs.getString("email"));
        } catch (Exception e) {
            System.out.println("Error getting user " + email);
            return null;
        }
    }

    private String updateSearchStatementCriteria(String statementStr, SearchCriteria sc) {
        boolean atLeastOneCriteria = false;
        if (sc.getQuadrant() != null) {
            if (atLeastOneCriteria) {
                statementStr += "AND ";
            }
            statementStr += "`quadrant` = '" + sc.getQuadrant() + "' ";
            atLeastOneCriteria = true;
        }
        if (sc.getType() != null) {
            if (atLeastOneCriteria) {
                statementStr += "AND ";
            }
            statementStr += "`type` = '" + sc.getType() + "' ";
            atLeastOneCriteria = true;
        }
        if (sc.isFurnished() != null) {
            if (atLeastOneCriteria) {
                statementStr += "AND ";
            }
            statementStr += "`isFurnished` = '" + sc.isFurnished() + "' ";
            atLeastOneCriteria = true;
        }
        if (sc.getNumOfBedrooms() != null) {
            if (atLeastOneCriteria) {
                statementStr += "AND ";
            }
            statementStr += "`numBedrooms` = '" + sc.getNumOfBedrooms() + "' ";
            atLeastOneCriteria = true;
        }
        if (sc.getNumOfBathrooms() != null) {
            if (atLeastOneCriteria) {
                statementStr += "AND ";
            }
            statementStr += "`numBathrooms` = '" + sc.getNumOfBathrooms() + "' ";
            atLeastOneCriteria = true;
        }
        return statementStr;
    }
}
