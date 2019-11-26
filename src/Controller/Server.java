package Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    /**
     * Socket for server
     */
    private ServerSocket serverSocket;
    private ExecutorService pool;

    public Server(int serv) {
        try {
            serverSocket = new ServerSocket(serv);
            pool = Executors.newCachedThreadPool();
            System.out.println("Rental system is running");
        } catch (IOException e) {
            System.err.println("Server Error");
            e.printStackTrace();
        }
    }

    private void communicate() {
        while(true) {
            try {
                RentalSystem theRentalSystem = new RentalSystem(serverSocket.accept());
                pool.execute(theRentalSystem);
            } catch (IOException e) {
                System.err.println("Server Error");
                e.printStackTrace();
            }
        }
    }

    public static void main(String [] args) {
        Server rentalServer = new Server(5555);
        rentalServer.communicate();
    }
}