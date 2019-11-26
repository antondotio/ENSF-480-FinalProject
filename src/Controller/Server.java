package Controller;

import Entity.*;
import Systems.DatabaseSystem;
import Systems.FinancialInstitutionSystem;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Server {
    /**
     * Socket for server
     */
    private ServerSocket serverSocket;
    /**
     * Printing back to client
     */
    private PrintWriter socketOut;
    /**
     * Socket for client
     */
    private Socket clientSocket;
    /**
     * Reading input from client
     */
    private BufferedReader socketIn;

    //  backend controllers
    private static LoginController login;
    private LandlordController landlordController;
    private RenterController renterController;
    private RegisteredRenterController registeredRenterController;
    private ManagerController managerController;
    private ListingController listingController;
    private NotificationController notificationController;

    private DatabaseSystem db;
    private FinancialInstitutionSystem fis;

    //  map account ids to account objects
    private Hashtable<Integer, LandlordAccount> loggedInLandlords;
    private Hashtable<Integer, RegisteredRenterAccount> loggedInRenters;
    private Hashtable<Integer, ManagerAccount> loggedInManagers;

    /**
     * Constructor for Server
     * @param portNumber port used for Socket
     */
    public Server(int portNumber) {
        db = new DatabaseSystem();

        login = LoginController.getInstance(db);
        loggedInLandlords = new Hashtable<>();
        loggedInRenters = new Hashtable<>();
        loggedInManagers = new Hashtable<>();

        initControllers();

        try {
            serverSocket = new ServerSocket(portNumber);
            clientSocket = serverSocket.accept();
            System.out.println("Server in now running...");
            socketOut = new PrintWriter(clientSocket.getOutputStream(), true);
            socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch(IOException e) {
            System.out.println("error in constructor");
        }
    }

    /**
     * Waits for client input then acts accordingly to the input
     */
    public void communicate() {
        String input = "";
        while(true) {
            try {
                //  all of these will print DONE for success if no return, ERROR if failure
                //  getlistings and getlandlordlistings will print several lines THEN print DONE
                //  login will ONLY print its info or ERROR, no DONE
                input = socketIn.readLine();
                System.out.println(input);
                //  NOTE: I chose to ignore email because since it's simulated we can handle it all on the front end
                if (input.startsWith("LOGIN-")) {
                    handleLogin(input);
                } else if (input.startsWith("SIGNUP-")) {
                    handleSignup(input);
                } else if(input.startsWith("GET/LISTINGS-")) {
                    //  expects parameter to be renter user id, or null if not registered
                    handleGetListings(input);
                } else if (input.startsWith("GET/LANDLORDLISTINGS-")) {
                    // expects parameter to be landlord ID
                    handleGetLandlordListings(input);
                } else if (input.equals("GET/ALLLISTINGS")) {
                    //  expects no parameters
                    handleGetAllListings();
                } else if (input.equals("GET/ALLRENTERS")) {
                    handleGetAllUsers("RENTER");
                } else if (input.equals("GET/ALLLANDLORDS")) {
                    handleGetAllUsers("LANDLORD");
                } else if (input.startsWith("POST/LISTING-")) {
                    handlePostListing(input);
                } else if (input.startsWith("POST/PAYMENT-")) {
                    //  expects listingId
                    //  e.g. POST/PAYMENT-1 will pay for listing 1
                    handlePayment(input);
                } else if (input.startsWith("POST/UPDATELISTINGFEES-")) {
                    //  expects listingId-newFee-newFeePeriod
                    //  e.g. POST/UPDATELISTINGFEES-1-30-30 will set listing 1's fee to $30 and it will last 30 days
                    //  THIS IS FOR THE NEXT PAYMENT CYCLE. DOES NOT AFFECT ACTIVE LISTINGS. WILL AFFECT THEM WHEN THEY EXPIRE.
                    handleUpdateListingFees(input);
                } else if (input.startsWith("GET/SUMMARYREPORT-")) {
                    handleGetSummary(input);
                } else if (input.startsWith("POST/CHANGESTATE-")) {
                    //  expects accountId-listingId-newState
                    //  e.g. POST/CHANGESTATE-3-1-Suspended will cause user 3 to try and change listing 1 to Suspended
                    handleChangeListingState(input);
                } else if (input.startsWith("GET/NOTIFICATIONS-")) {
                    //  expects accountId
                    //  e.g. GET/NOTIFICATIONS-1 will try to get notifications for renter with user id 1
                    handleGetNotifications(input);
                }  else if (input.startsWith("GET/SEARCHCRITERIA-")) {
                    handleGetSearchCriteria(input);
                } else if (input.startsWith("POST/SUBSCRIBE-")) {
                    handleSubscribe(input);
                } else if (input.startsWith("POST/UNSUBSCRIBE-")) {
                    handleUnsubscribe(input);

                } else if (input.startsWith("EMAIL-")) {
                    socketOut.println("DONE");
                }
            } catch(Exception e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void handlePayment(String input) {
        String[] params = parseParams(input);
        //  mark correct listingid as paid, create a payment object
        Payment payment = listingController.pay(Integer.parseInt(params[0]));
        if (payment == null) {
            System.out.println("ERROR: Failed to pay for listing with id " + params[0] + ".");
            socketOut.println("ERROR");
        } else {
            fis.submitPayment(payment); // send payment to financial institution
            socketOut.println("DONE");
        }
    }

    public void handleLogin(String input) {
        String[] params = parseParams(input);
        String accInfo = login.authenticate(params[0], params[1]);
        if (accInfo != null) {
            String[] accInfoSplit = accInfo.split("-");
            addAccount(params[0], params[1], Integer.parseInt(accInfoSplit[0]), accInfoSplit[1]);
            socketOut.println(accInfo);
        } else {
            socketOut.println("ERROR");
        }
    }

    public void handleSignup(String input) {
        String[] params = parseParams(input);
        bool signupStatus = login.signup(params[0], params[1])
        if (signupStatus) {
            socketOut.println("DONE");
        } else {
            socketOut.println("ERROR");
        }
    }

    public void handleGetListings(String input) {
        String[] params = parseParams(input);
        ArrayList<Listing> listings;
        //  get listings
        listings = renterController.getListings(Parsing.parseAny(params[1]), Parsing.parseInt(params[2]), Parsing.parseDouble(params[3]), Parsing.parseFurnished(params[4]), Parsing.parseAny(params[5]));
        //  print listings
        printRenterListings(listings);
        socketOut.println("DONE");
    }

    public void handleSubscribe(String input) {
        String[] params = parseParams(input);
        if (registeredRenterController.subscribe(Integer.parseInt(params[0]), Parsing.parseAny(params[1]), Parsing.parseInt(params[2]), Parsing.parseDouble(params[3]),
                Parsing.parseFurnished(params[4]), Parsing.parseAny(params[5]))) {
            socketOut.println("DONE");
        } else {
            socketOut.println("ERROR");
        }
    }

    public void handleUnsubscribe(String input) {
        String [] params = parseParams(input);
        if (registeredRenterController.unsubscribe(Integer.parseInt(params[0]))) {
            socketOut.println("DONE");
        } else {
            socketOut.println("ERROR");
        }
    }

    public void handleGetSummary(String input) {
        String[] params = parseParams(input);
        LocalDate startDate = LocalDate.parse(params[0] + "-" + params[1] + "-" + params[2]);
        LocalDate endDate = LocalDate.parse(params[3] + "-" + params[4] + "-" + params[5]);
        String[] summary = managerController.getSummary(startDate, endDate);
        for (String line : summary) {
            socketOut.println(line);
        }
        socketOut.println("DONE");
    }

    public void handleGetSearchCriteria(String input) {
        String[] params = parseParams(input);
        ArrayList<SearchCriteria> criterias = registeredRenterController.getSearchCriteria(Integer.parseInt(params[0]));
        if (criterias != null) {
            for (SearchCriteria sc : criterias) {
                socketOut.println(sc.getId() + "\t\t\t\t" + nullObjectToString(sc.getQuadrant()) + "\t\t\t\t\t\t" +
                        nullObjectToString(sc.getType()) + "\t\t\t" + nullObjectToString(sc.getNumOfBedrooms()) + "\t\t\t\t" + nullObjectToString(sc.getNumOfBathrooms()) +
                        "\t\t\t\t" + nullObjectToString(sc.isFurnished()));
            }
        }
        socketOut.println("DONE");
    }

    public void handleGetLandlordListings(String input) {
        String[] params = parseParams(input);
        ArrayList<Listing> listings = landlordController.getListings(Integer.parseInt(params[0]));
        printDetailedListingsResults(listings);
        socketOut.println("DONE");
    }

    public void handleGetAllListings() {
        ArrayList<Listing> listings = managerController.getListings();
        printDetailedListingsResults(listings);
        socketOut.println("DONE");
    }

    public void handlePostListing(String input) {
        String[] params = parseParams(input);
        if (listingController.postListing(Integer.parseInt(params[0]), params[1], Integer.parseInt(params[2]),
                Double.parseDouble(params[3]), Boolean.parseBoolean(params[4]), params[5],
                params[6], params[7], params[8], params[9])) {
            socketOut.println("DONE");
        } else {
            System.out.println("Failed to post listing for user: " + params[0]);
            socketOut.println("ERROR");
        }
    }

    public void handleGetAllUsers(String userType) {
        ArrayList<Account> accounts = managerController.getUsersOfType(userType);
        if (accounts != null) {
            for (Account a : accounts) {
                socketOut.println(a.getAccountID() + "\t\t\t" + a.getName().toString() + "\t\t" + a.getEmail());
            }
        }
        socketOut.println("DONE");
    }

    public void handleGetNotifications(String input) {
        String[] params = parseParams(input);
        ArrayList<Listing> listings = notificationController.getNotifications(Integer.parseInt(params[0]));
        printRenterListings(listings);
        socketOut.println("DONE");
    }

    public void handleUpdateListingFees(String input) {
        String[] params = parseParams(input);
        if (listingController.updateListingFees(Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[2]))) {
            socketOut.println("DONE");
        } else {
            System.out.println("ERROR: Failed to change listing with id " + params[0] + "'s fees to " + params[1] + " and its fee period to " + params[2]);
            socketOut.println("ERROR");
        }
    }

    public void handleChangeListingState(String input) {
        String[] params = parseParams(input);
        boolean updatedSuccessfully = false;
        Integer userId = Integer.parseInt(params[0]);
        int listingId = Integer.parseInt(params[1]);
        String oldState = listingController.checkListingState(listingId);
        if (loggedInLandlords.containsKey(userId)) {
            updatedSuccessfully = landlordController.updateListingState(listingId, oldState, params[2]);
        } else if (loggedInManagers.containsKey(userId)) {
            updatedSuccessfully = managerController.updateListingState(listingId, oldState, params[2]);
        } else {
            System.out.println("User with id " + params[0] + " tried to change listing state of listing " + params[1] + " and failed. Unable to find user in list of logged in users.");
            socketOut.println("ERROR");
            return;
        }
        if (updatedSuccessfully) {
            socketOut.println("DONE");
        } else {
            System.out.println("User with id " + params[0] + " tried to change listing state of listing " + params[1] + " and failed.");
            socketOut.println("ERROR");
        }
    }

    private void addAccount(String email, String password, int id, String type) {
        if (type.equals("LANDLORD")) {
            loggedInLandlords.put(id, db.getLandlordAccount(email, password));
        } else if (type.equals("RENTER")) {
            loggedInRenters.put(id, db.getRenterAccount(email, password));
        } else if (type.equals("MANAGER")) {
            loggedInManagers.put(id, db.getManagerAccount(email, password));
        }
    }

    public void printRenterListings(ArrayList<Listing> listings) {
        if (listings != null) {
            for (Listing l : listings) {
                if (l.getStatus().equals("Active")) {
                    socketOut.println(l.getListingIDnumber() + "\t\t\t" + padAddress(l.getProperty().getAddress().toString()) + "\t\t\t" +
                            l.getProperty().getQuadrant() + "\t\t\t" + l.getProperty().getType() + "\t\t\t" + l.getProperty().getNumOfBedrooms() +
                            "\t\t\t\t" + l.getProperty().getNumOfBathrooms() + "\t\t\t\t" + l.getProperty().isFurnished());
                }
            }
        }
    }

    public void printDetailedListingsResults(ArrayList<Listing> listings) {
        if (listings != null) {
            for (Listing l : listings) {
                socketOut.println(l.getListingIDnumber() + "\t\t\t" + nullDateToString(l.getListingStart()) + "\t\t" + nullDateToString(l.getListingEnd()) + "\t\t" +
                        getStatusPadded(l.getStatus()) + "\t\t" + l.getPaymentFee() + "\t\t\t" + l.isFeePaid() + "\t\t\t" + l.getFeePeriod() + "\t\t\t" + padAddress(l.getProperty().getAddress().toString()) +
                        "\t\t" + l.getProperty().getQuadrant() + "\t\t\t\t" + l.getProperty().getType() + "\t\t\t" + l.getProperty().getNumOfBedrooms() + "\t\t\t\t" + l.getProperty().getNumOfBathrooms()
                        + "\t\t\t" + l.getProperty().isFurnished());
            }

        }
    }

    public String getStatusPadded(String status) {
        if (!status.equals("Suspended")) {
            status += "\t";
            return status;
        }
        return status;
    }

    public String padAddress(String address) {
        StringBuilder newAddress = new StringBuilder(address);
        while (newAddress.length() < 50) {
            newAddress.append(" ");
        }
        return newAddress.toString();
    }

    private String nullObjectToString(Object o) {
        return o != null ? o.toString() : "N/A\t";
    }

    private String nullDateToString(LocalDate d) {
        return d != null ? d.toString() : "N/A\t\t";
    }

    public String[] parseParams(String input) {
        String[] split = input.split("-");
        return Arrays.copyOfRange(split, 1, split.length);
    }

    public void initControllers() {
        landlordController = new LandlordController(db);
        renterController = new RenterController(db);
        registeredRenterController = new RegisteredRenterController(db);
        managerController = new ManagerController(db);
        listingController = new ListingController(db);
        notificationController = new NotificationController(db, listingController);

        landlordController.setListingController(listingController);
        managerController.setListingController(listingController);
    }

    public void setFinancialInstitutionSystem(FinancialInstitutionSystem fis) {
        this.fis = fis;
    }

    /**
     * CLoses the socket, printwrite and bufferedreader
     */
    public void close() {
        try {
            socketIn.close();
            socketOut.close();
            serverSocket.close();
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates server object and runs communicate class
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Server server = new Server(5050);
        FinancialInstitutionSystem fis = new FinancialInstitutionSystem();
        server.setFinancialInstitutionSystem(fis);
        server.communicate();
    }

}
