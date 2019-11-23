package Controller;

import Entity.LandlordAccount;
import Entity.ManagerAccount;
import Entity.RegisteredRenterAccount;
import Systems.DatabaseSystem;

import java.io.IOException;
import java.net.*;
import java.io.*;
import java.util.*;
import Entity.Account;
import Entity.Listing;

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

    private DatabaseSystem db;

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
//        ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
//        readSuppliers(suppliers);
//        Inventory theInventory = new Inventory(readItems(suppliers));
//        Shop theShop = new Shop(theInventory, suppliers);
        String input = "";
        while(true) {
            try {
                //  all of these will print DONE for success if no return, ERROR if failure
                //  getlistings will print several lines THEN print DONE
                //  login will ONLY print its info or ERROR, no DONE
                input = socketIn.readLine();
                System.out.println(input);
                //  NOTE: I chose to ignore email because since it's simulated we can handle it all on the front end
                if(input.startsWith("GET/LISTINGS-")) {
                    handleGetListings(input);
                } else if (input.startsWith("POST/LISTING-")) {
                    String[] params = parseParams(input);
                    socketOut.println("NULL");
                    socketOut.println("DONE");               
                } else if (input.startsWith("POST/PAYMENT-")) {
                    String[] params = parseParams(input);
                    // update
                    socketOut.println("DONE");
                } else if (input.startsWith("LOGIN-")) {
                    handleLogin(input);
                } else if (input.startsWith("POST/UPDATELISTING-")) {
                    //  expects listingId-newFee-newFeePeriod
                    //  e.g. POST/UPDATELISTING-1-35-30 will set listing 1's fee to $35 and it will last 35 days on next payment
                    handleUpdateListing(input);
                } else if (input.equals("GET/SUMMARYREPORT")) {
                    socketOut.println("NULL");
                } else if (input.startsWith("POST/CHANGESTATE-")) {
                    //  expects accountId-listingId-newState
                    //  e.g. POST/CHANGESTATE-3-1-Suspended will cause user 3 to try and change listing 1 to Suspended
                    handleChangeListingState(input);
                } else if (input.startsWith("EMAIL")) {
                    socketOut.println("DONE");
                }
            } catch(Exception e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void handleLogin(String input) {
        String[] params = parseParams(input);
        String accInfo = login.authenticate(params[0], params[1]);
        String[] accInfoSplit = accInfo.split("-");
        addAccount(params[0], params[1], Integer.parseInt(accInfoSplit[0]), accInfoSplit[1]);
        System.out.println(accInfo);
        if (accInfo != null) {
            socketOut.println(accInfo);
        } else {
            socketOut.println("ERROR");
        }
    }

    public void handleGetListings(String input) {
        String[] params = parseParams(input);
        ArrayList<Listing> listings;
        //  get listings
        if (params[0].equals("NULL")) {
            //  use rentercontroller
            listings = renterController.getListings(Parsing.parseAny(params[1]), Parsing.parseInt(params[2]), Parsing.parseDouble(params[3]),
                    Parsing.parseFurnished(params[4]), Parsing.parseAny(params[5]));
        } else {
            //  use registeredrentercontroller
            listings = registeredRenterController.getListings(Integer.parseInt(params[0]), Parsing.parseAny(params[1]), Parsing.parseInt(params[2]),
                    Parsing.parseDouble(params[3]), Parsing.parseFurnished(params[4]), Parsing.parseAny(params[5]));
        }
        //  print listings
        if (listings != null) {
            for (Listing l : listings) {
                if (l.getStatus().equals("Active")) {
                    socketOut.println(l.getListingIDnumber() + "\t\t\t" + l.getProperty().getAddress().toString() + "\t\t\t" +
                            l.getProperty().getQuadrant() + "\t\t\t" + l.getProperty().getType() + "\t\t\t" + l.getProperty().getNumOfBedrooms() +
                            "\t\t\t\t" + l.getProperty().getNumOfBathrooms() + "\t\t\t\t" + l.getProperty().isFurnished());
                }
            }
        }
        socketOut.println("DONE");
    }

    public void handleUpdateListing(String input) {
        String[] params = parseParams(input);
        socketOut.println("DONE");
    }

    public void handleChangeListingState(String input) {
        String[] params = parseParams(input);
        boolean updatedSuccessfully = false;
        Integer userId = Integer.parseInt(params[0]);
        if (loggedInLandlords.containsKey(userId)) {
            updatedSuccessfully = landlordController.updateListingState(Integer.parseInt(params[1]), params[2]);
        } else if (loggedInManagers.containsKey(userId)) {
            updatedSuccessfully = managerController.updateListingState(Integer.parseInt(params[1]), params[2]);
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
        if (type == "LANDLORD") {
            loggedInLandlords.put(id, db.getLandlordAccount(email, password));
        } else if (type == "RENTER") {
            //  TODO: need to add notification functionality on login
            loggedInRenters.put(id, db.getRenterAccount(email, password));
        } else if (type == "MANAGER") {
            loggedInManagers.put(id, db.getManagerAccount(email, password));
        }
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

        landlordController.setListingController(listingController);
        managerController.setListingController(listingController);
    }

    /**
     * CLoses the socket, printwrite and bufferedreader
     */
    public void close() {
        try {
            socketIn.close();
            socketOut.close();
            serverSocket.close();
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
        server.communicate();
    }

}
