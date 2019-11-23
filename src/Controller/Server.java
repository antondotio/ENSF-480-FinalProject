package Controller;

import Systems.DatabaseSystem;

import java.io.IOException;
import java.net.*;
import java.io.*;
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


    /**
     * Constructor for Server
     * @param portNumber port used for Socket
     */
    public Server(int portNumber) {
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
        String input = "GET/LISTINGS-Calgary/NE/900/1200";
        System.out.println(input);
        while(true) {
            try {
                input = socketIn.readLine();
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
                    String[] params = parseParams(input);
                    socketOut.println("NULL");
                    socketOut.println("DONE");
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
                }
            } catch(Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public String[] parseParams(String input) {
        String[] splitQueryAndParams = input.split("-");
        return splitQueryAndParams[1].split("/");
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
        DatabaseSystem db = new DatabaseSystem();
        server.communicate();
    }

}
