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
        String input = "";
        while(true) {
            try {
                input = socketIn.readLine();
                System.out.println(input);
//                if(input.equals("GET/TOOLS")) {
//                    getTools(theShop);
//                }
            } catch(IOException e) {
                System.err.println(e.getMessage());
            }
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
        DatabaseSystem db = new DatabaseSystem();
        server.communicate();
    }

}
