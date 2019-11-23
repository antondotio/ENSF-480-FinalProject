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

    private static LoginController login;

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
                input = socketIn.readLine();
                System.out.println(input);
                //  NOTE: I chose to ignore email because since it's simulated we can handle it all on the front end
                if(input.startsWith("GET/LISTINGS-")) {
                    String[] params = parseParams(input);
                    socketOut.println("NULL");
                    socketOut.println("DONE");               
                } else if (input.startsWith("POST/PROPERTY-")) {
                    String[] params = parseParams(input);
                    socketOut.println("DONE");
                } else if (input.startsWith("POST/FEE-")) {
                    String[] params = parseParams(input);
                    // create listing and make it visible for renters
                    // and notify renters
                    socketOut.println("DONE");
                } else if (input.startsWith("LOGIN-")) {
                    handleLogin(input);
                } else if (input.startsWith("POST/UPDATELISTING-")) {
                    String[] params = parseParams(input);
                    socketOut.println("DONE");
                } else if (input.startsWith("GET/SINGLELISTING-")) {
                    String[] params = parseParams(input);
                    socketOut.println("NULL");
                } else if (input.equals("GET/SUMMARYREPORT")) {
                    socketOut.println("NULL");
                } else if (input.startsWith("POST/CHANGESTATE")) {
                    String[] params = parseParams(input);
                    socketOut.println("NULL");
                } else if (input.startsWith("EMAIL")) {
                    socketOut.println("DONE");
                }
            } catch(Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void handleLogin(String input) {
        String[] params = parseParams(input);
        String accInfo = login.authenticate(params[0], params[1]);
        String[] accInfoSplit = accInfo.split("-");
        addAccount(params[0], params[1], Integer.parseInt(accInfoSplit[0]), accInfoSplit[1]);

        if (accInfo != null) {
            socketOut.println(accInfo);
        } else {
            socketOut.println("ERROR");
        }
    }

    public String[] parseParams(String input) {
        String[] splitQueryAndParams = input.split("-");
        return splitQueryAndParams[1].split("/");
    }

    private void addAccount(String email, String password, int id, String type) {
        if (type == "LANDLORD") {
            loggedInLandlords.put(id, db.getLandlordAccount(email, password));
        } else if (type == "RENTER") {
            loggedInRenters.put(id, db.getRenterAccount(email, password));
        } else if (type == "MANAGER") {
            loggedInManagers.put(id, db.getManagerAccount(email, password));
        }
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
