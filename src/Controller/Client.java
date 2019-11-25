package Controller;

import java.net.Socket;
import java.util.ArrayList;
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
        if(username.equals("") || password.equals("")) {
            return "ERROR";
        }
        try {
            socketOut.println("LOGIN-" + username + "-" + password);
            String response = socketIn.readLine();
            if(response.equals("ERROR")) {
                return response;
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
            socketOut.println("GET/LISTINGS-" + checkNull(accountID) +
                "-" + type + "-" + checkNull(beds) + "-" + checkNull(baths) + 
                "-" + checkNull(furnished) + "-" + quad);
            String response = socketIn.readLine();
            String listings = "";
            while(!response.equals("DONE"))
            {
                listings += response;
                listings += "\n";
                response = socketIn.readLine();
            }
            return listings;
        } catch(Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return "ERROR";
        }
    }

    public String postListing(String type, String bedrooms, String baths, String furnished,
    String quad, String street, String city, String country, String postalCode) {
        if(type.equals("") || bedrooms.equals("") || baths.equals("") || street.equals("") 
        || city.equals("") || country.equals("") || postalCode.equals("")) {
            return "ERROR";
        }
        try {
            socketOut.println("POST/LISTING-" + accountID.toString() + "-" + type + "-" + bedrooms + "-" + baths +
                "-" + furnished + "-" + quad + "-" + street + "-" + city + "-" + country + "-" + postalCode);
            return socketIn.readLine();
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return "ERROR";
        }
    }

    public String getLandlordListings() {
        try {
            socketOut.println("GET/LANDLORDLISTINGS-" + accountID.toString());
            String response = socketIn.readLine();
            String listings = "";
            while(!response.equals("DONE"))
            {
                listings += response;
                listings += "\n";
                response = socketIn.readLine();
            }
            return listings;
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return "ERROR";
        }
    }

    public String changeListingState(String listingID, String newState) {
        if(listingID.equals("") || newState.equals("")) {
            return "ERROR";
        }
        try {
            socketOut.println("POST/CHANGESTATE-" + accountID.toString() + "-" + listingID + "-" + newState);
            return socketIn.readLine();
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return "ERROR";
        }
    }

    public String payFee(String listingID) {
        if(listingID.equals("")) {
            return "ERROR";
        }
        try {
            socketOut.println("POST/PAYMENT-" + listingID);
            return socketIn.readLine();
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return "ERROR";
        }
    }

    public String updateListingFees(String listingID, String newFee, String newPeriod) {
        if(listingID.equals("") || newFee.equals("") || newPeriod.equals("")) {
            return "ERROR";
        }
        try {
            socketOut.println("UPDATELISTINGFEES-" + listingID + "-" + newFee + "-" + newPeriod);
            return socketIn.readLine();
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return "ERROR";
        }
    }

    public String getSummary(String startDate, String endDate) {
        if(startDate.equals("") || endDate.equals("")) {
            return "ERROR";
        }
        try {
            socketOut.println("POSTSUMMARYREPORT-" + startDate + "-" + endDate);
            String response = socketIn.readLine();
            String summary = "";
            while(!response.equals("DONE"))
            {
                summary += response;
                summary += "\n";
                response = socketIn.readLine();
            }
            return summary;
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return "ERROR";
        }
    }

    public String getAllRenters() {
        try {
            socketOut.println("GET/ALLRENTERS");
            String response = socketIn.readLine();
            String renters = "";
            while(!response.equals("DONE"))
            {
                renters += response;
                renters += "\n";
                response = socketIn.readLine();
            }
            return renters; 
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return "ERROR";
        }
    }

    public String getAllLandlords() {
        try {
            socketOut.println("GET/ALLLANDLORDS");
            String response = socketIn.readLine();
            String landlords = "";
            while(!response.equals("DONE"))
            {
                landlords += response;
                landlords += "\n";
                response = socketIn.readLine();
            }
            return landlords; 
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return "ERROR";
        }
    }

    public String getAllListings() {
        try {
            socketOut.println("GET/ALLLISTINGS");
            String response = socketIn.readLine();
            String properties = "";
            while(!response.equals("DONE"))
            {
                properties += response;
                properties += "\n";
                response = socketIn.readLine();
            }
            return properties; 
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return "ERROR";
        }
    }

    public String sendEmail(String listingID, String message) {
        if(listingID.equals("") || message.equals("")) {
            return "ERROR";
        }
        try {
            socketOut.println("EMAIL-" + checkNull(accountID) + "-" + listingID + "-" + message);
            return socketIn.readLine();
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return "ERROR";
        }
    }

    public String getSearchCriteria() {
        try {
            socketOut.println("GET/SEARCHCRITERIA-" + accountID.toString());
            String response = socketIn.readLine();
            String searchCriterias = "";
            while(!response.equals("DONE"))
            {
                searchCriterias += response;
                searchCriterias += "\n";
                response = socketIn.readLine();
            }
            return searchCriterias; 
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return "ERROR";
        }
    }

    public String subscribe(String type, String beds, String baths, String furnished, String quad) {
        try {
            socketOut.println("POST/SUBSCRIBE-" + accountID.toString() +
                "-" + type + "-" + checkNull(beds) + "-" + checkNull(baths) +
                "-" + checkNull(furnished) + "-" + quad);
            return socketIn.readLine();
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return "ERROR";
        }
    }

    public String unsubscribe(String searchID) {
        if(searchID.equals("")) {
            return "ERROR";
        }
        try {
            socketOut.println("POST/UNSUBSCRIBE-" + searchID);
            return socketIn.readLine();
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return "ERROR";
        }
    }

    public String getNotifications() {
        try {
            socketOut.println("GET/NOTIFICATIONS-" + accountID.toString());
            String response = socketIn.readLine();
            String notifications = "";
            while(!response.equals("DONE"))
            {
                notifications += response;
                notifications += "\n";
                response = socketIn.readLine();
            }
            return notifications;
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
        if(input == null || input.equals(""))
            return "NULL";
        return input;
    }

    public String checkNull(Integer input) {
        if(input == null)
            return "NULL";
        return input.toString();
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
