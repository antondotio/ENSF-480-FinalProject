package Controller;

import java.net.Socket;
import java.io.*;
import java.io.IOException;


public class Client {
    /**
     * Socket to connect to.
     */
    private Socket socket;
    /**
     * Input from socket.
     */
    private BufferedReader socketIn;
    /**
     * Output to socket.
     */
    private PrintWriter socketOut;

    private Integer accountID;

    /**
     * Constructs a new Client.
     * @param serverName Servername to connect to.
     * @param portNumber Port on server to connect to.
     */
    public Client(String serverName, int portNumber) {
        accountID = null;
        try {
            socket = new Socket(serverName, portNumber);
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketOut = new PrintWriter((socket.getOutputStream()), true);
        } catch (IOException e) {
            System.err.println(e.getStackTrace());
        }
    }

    public String login(String username, String password) {
        try {
            socketOut.println("LOGIN-" + username + "-" + password);
            String response = socketIn.readLine();
            if(response.equals("ERROR")) {
                return "ERROR";
            }
            String [] params = parseParams(response);
            accountID = Integer.parseInt(params[0]);
            System.out.println(response);
            return params[1];
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            return "ERROR";
        }
    }

    public String getListings(String type, String beds,
        String baths, String furnished, String quad) {
        try {
            socketOut.println("GET/LISTING-" + checkNull(accountID.toString()) +
                "-" + type + "-" + checkNull(beds) + "-" + checkNull(baths) + 
                "-" + checkNull(furnished) + "-" + quad);
            String response = socketIn.readLine();
            String listings = "";
            while(!response.equals("DONE"))
            {
                listings += response;
                listings += "\n";
            }
            return listings;
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return "ERROR";
        }
    }



    public String changeListingState(String listingID, String newState) {
        try {
            socketOut.println("POST/CHANGESTATE-" + listingID + "-" + newState);
            return socketIn.readLine();
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return "ERROR";
        }
    }


//    /**
//     * Display all tools in the shop.
//     * @return A string containing all tools in the shop.
//     * @throws IOException In case there's an input/output error with the socket.
//     */
//    public String displayAllTools() throws IOException {
//        socketOut.println("GET/TOOLS");
//        String response = socketIn.readLine();
//        StringBuilder data = new StringBuilder();
//        while (! response.equals("GET/TOOLS")) {
//            data.append(response);
//            data.append("\n");
//            response = socketIn.readLine();
//        }
//        return data.toString();
//    }

    public String[] parseParams(String input) {
        return input.split("-");
    }

    public String checkNull(String input) {
        if(input.equals(""))
            return "NULL";
        return input;
    }

    /**
     * Closes all connections to the socket.
     */
    public void close() {
        try {
            socketIn.close();
            socketOut.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Closing error: " + e.getMessage());
        }
    }
}
