package Controller;

import java.util.ArrayList;

public class Listener {
    
    private Client client;
    private static Listener listener = null;

    private Listener() {
        listener = this;
    }

    static public Listener getListener()
    {
        if(listener == null)
            listener = new Listener();
        return listener;
    }

    public void setClient(Client c)
    {
        client = c;
    }

    public String getListings() {
        String listings = "hi";
        return listings;
    }
}