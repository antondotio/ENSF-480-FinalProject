package Systems;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import Entity.*;

public class DatabaseSystem {
    Connection connection;
    Statement statement;
    PreparedStatement pStatement;
    ResultSet rs;

    public DatabaseSystem() {
        //  TODO: close the connection/statement at some point lol
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
            if (rs.next()) {
                return rs.getInt("id") + "-" + rs.getString("type");
            } else {
                throw new Exception("No results returned for logging in " + email);
            }
        } catch (Exception e) {
            System.out.println("Error logging in user " + email);
            return null;
        }
    }

    public ArrayList<Listing> getListings(SearchCriteria sc) {
        try {
            StringBuilder statementStr = new StringBuilder("SELECT * FROM `Listing` AS `L`, `Property` AS `P` ");
            updateSearchStatementCriteria(statementStr, sc);
            statement = connection.createStatement();
            rs = statement.executeQuery(statementStr.toString());

            return handleListingsResults();
        } catch (SQLException e) {
            System.out.println("Failed to retrieve listings.");
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Listing> getLandlordListings(int landlordId) {
        try {
            String statementStr = "SELECT * FROM `Listing` AS `L`, `Property` AS `P` WHERE L.landlordId=?";
            pStatement = connection.prepareStatement(statementStr);
            pStatement.setInt(1, landlordId);
            rs = pStatement.executeQuery();

            return handleListingsResults();
        } catch (SQLException e) {
            System.err.println("Database Error: Failed to retrieve listings. For landlord " + landlordId);
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Listing> getAllListings() {
        try {
            String statementStr = "SELECT * FROM `Listing` AS `L`, `Property` AS `P`";
            pStatement = connection.prepareStatement(statementStr);
            rs = pStatement.executeQuery();

            return handleListingsResults();
        } catch (SQLException e) {
            System.err.println("Database Error: Failed to retrieve ALL listings at once. ");
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Account> getUsersOfType(String userType) {
        try {
            String statementStr = "SELECT * FROM `Account` WHERE `type`=?";
            pStatement = connection.prepareStatement(statementStr);
            pStatement.setString(1, userType);
            rs = pStatement.executeQuery();

            ArrayList<Account> accounts = new ArrayList<>();
            while (rs.next()) {
                accounts.add(new Account(new Name(rs.getString("fname"), rs.getString("lname")),
                        rs.getInt("id"), rs.getString("email")));
            }
            return accounts;
        } catch (SQLException e) {
            System.err.println("Database Error: Failed to retrieve ALL users of type: " + userType);
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<Listing> handleListingsResults() throws SQLException {
        ArrayList<Listing> listings = new ArrayList<>();
        while (rs.next()) {
            listings.add(new Listing(rs.getInt("landlordId"),
                    new Property(rs.getString("type"), rs.getInt("numBedrooms"),
                            rs.getDouble("numBathrooms"), rs.getBoolean("isFurnished"),
                            rs.getString("quadrant"),
                            new Address(rs.getString("street"), rs.getString("city"),
                                    rs.getString("country"), rs.getString("postalCode")),
                            rs.getInt("P.id")),
                    rs.getObject("listingStart", LocalDate.class), rs.getObject("listingEnd", LocalDate.class),
                    rs.getDouble("fee"), rs.getString("status"), rs.getInt("L.id"),
                    rs.getObject("listingAddedDate", LocalDate.class), rs.getBoolean("paid")));
        }
        return listings;
    }
    public boolean updateListingFees(int listingId, int newFee, int newFeePeriodInDays) {
        try {
            String statementStr = "UPDATE `Listing` SET `fee`=?, `feePeriod`=? WHERE `id`=?";
            pStatement = connection.prepareStatement(statementStr);
            pStatement.setInt(1, newFee);
            pStatement.setInt(2, newFeePeriodInDays);
            pStatement.setInt(3, listingId);
            pStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("Database error when trying to update fees of listing " + listingId + " to " + newFee + " and its fee period to " + newFeePeriodInDays);
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateListingState(int listingId, String newState) {
        try {
            String statementStr = "UPDATE `Listing` SET `status`=? WHERE `id`=?";
            pStatement = connection.prepareStatement(statementStr);
            pStatement.setString(1, newState);
            pStatement.setInt(2, listingId);
            pStatement.executeUpdate();

            return true;
        } catch(SQLException e) {
            System.out.println("Database error when trying to set state of listing " + listingId + " to " + newState);
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    //  set listing to active, set appropriate start and end dates, mark as not paid for next cycle
    public boolean activateListing(int listingId) {
        try {
            String statementStr = "SELECT `feePeriod` FROM Listing WHERE `id`=?";
            pStatement = connection.prepareStatement(statementStr);
            pStatement.setInt(1, listingId);
            rs = pStatement.executeQuery();

            int feePeriod;
            if (rs.next()) {
                feePeriod = rs.getInt("feePeriod");
            } else {
                throw new SQLException("Unable to fetch feePeriod for listing with id " + listingId);
            }

            statementStr = "UPDATE `Listing` SET `paid`=?, `listingStart`=?, `listingEnd`=? WHERE `id`=?";
            pStatement = connection.prepareStatement(statementStr);
            pStatement.setBoolean(1, false);
            pStatement.setObject(2, LocalDate.now());
            pStatement.setObject(3, LocalDate.now().plusDays(feePeriod));
            pStatement.setInt(4, listingId);
            pStatement.executeUpdate();

            return true;
        } catch(SQLException e) {
            System.out.println("Database error when trying activate listing " + listingId);
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    //  update paid state of listing in db, then retrieve listing that you paid for so we can create a payment object.
    public Payment pay(int listingId) {
        try {
            String statementStr = "UPDATE `Listing` SET `paid`=? WHERE `id`=?";
            pStatement = connection.prepareStatement(statementStr);
            pStatement.setBoolean(1, true);
            pStatement.setInt(2, listingId);
            pStatement.executeUpdate();

            statementStr = "SELECT `id`, `landlordId`, `fee` FROM `Listing` WHERE `id`=?";
            pStatement = connection.prepareStatement(statementStr);
            pStatement.setInt(1, listingId);
            rs = pStatement.executeQuery();

            if (rs.next()) {
                return new Payment(LocalDate.now(), rs.getInt("fee"), rs.getInt("id"), rs.getInt("landlordId"));
            } else {
                throw new SQLException("Database error when attempting to retrieve listing " + listingId + " after payment.");
            }
        } catch (SQLException e) {
            System.out.println("Database error when trying to pay for listing " + listingId);
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void insertSearchCriteria(SearchCriteria sc) {
        try {
            String statementStr = "INSERT INTO `SearchCriteria` (`quadrant`, `isFurnished`, `numOfBathrooms`, `numOfBedrooms`, `type`) values(?, ?, ?, ?, ?)";
            PreparedStatement pStmt = connection.prepareStatement(statementStr);
            pStmt.setObject(1, sc.getQuadrant(), JDBCType.VARCHAR);
            pStmt.setObject(2, sc.isFurnished(), JDBCType.BOOLEAN);
            pStmt.setObject(3, sc.getNumOfBathrooms(), JDBCType.DOUBLE);
            pStmt.setObject(4, sc.getNumOfBedrooms(), JDBCType.INTEGER);
            pStmt.setObject(5, sc.getType(), JDBCType.VARCHAR);

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
            if (rs.next()) {
                return new LandlordAccount(new Name(rs.getString("fname"), rs.getString("lname")),
                        rs.getInt("id"), rs.getString("email"));
            } else {
                throw new Exception("No results returned for logging in " + email);
            }
        } catch (Exception e) {
            System.out.println("Error getting user " + email);
            return null;
        }
    }

    public ManagerAccount getManagerAccount(String email, String password) {
        try {
            rs = getAccount(email, password);
            if (rs.next()) {
                return new ManagerAccount(new Name(rs.getString("fname"), rs.getString("lname")),
                        rs.getInt("id"), rs.getString("email"));
            } else {
                throw new Exception("No results returned for logging in " + email);
            }
        } catch (Exception e) {
            System.out.println("Error getting user " + email);
            return null;
        }
    }

    public RegisteredRenterAccount getRenterAccount(String email, String password) {
        try {
            rs = getAccount(email, password);
            if (rs.next()) {
                return new RegisteredRenterAccount(new Name(rs.getString("fname"), rs.getString("lname")),
                        rs.getInt("id"), rs.getString("email"));
            } else {
                throw new Exception("No results returned for logging in " + email);
            }
        } catch (Exception e) {
            System.out.println("Error getting user " + email);
            return null;
        }
    }

    private void updateSearchStatementCriteria(StringBuilder statementStr, SearchCriteria sc) {
        boolean atLeastOneCriteria = false;
        if (sc.getQuadrant() != null) {
            atLeastOneCriteria = handleIfFirst(statementStr, atLeastOneCriteria);
            statementStr.append("`quadrant` = '" + sc.getQuadrant() + "' ");
        }
        if (sc.getType() != null) {
            atLeastOneCriteria = handleIfFirst(statementStr, atLeastOneCriteria);
            statementStr.append("`type` = '" + sc.getType() + "' ");
        }
        if (sc.isFurnished() != null) {
            atLeastOneCriteria = handleIfFirst(statementStr, atLeastOneCriteria);
            statementStr.append("`isFurnished` = '" + (sc.isFurnished() ? '1' : '0') + "' ");
        }
        if (sc.getNumOfBedrooms() != null) {
            atLeastOneCriteria = handleIfFirst(statementStr, atLeastOneCriteria);
            statementStr.append("`numBedrooms` = '" + sc.getNumOfBedrooms() + "' ");
        }
        if (sc.getNumOfBathrooms() != null) {
            atLeastOneCriteria = handleIfFirst(statementStr, atLeastOneCriteria);
            statementStr.append("`numBathrooms` = '" + sc.getNumOfBathrooms() + "' ");
        }
    }

    private boolean handleIfFirst(StringBuilder statementStr, boolean atLeastOneCriteria) {
        if (atLeastOneCriteria) {
            statementStr.append("AND ");
            return true;
        }
        statementStr.append("WHERE ");
        atLeastOneCriteria = true;
        return true;
    }
}
