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

    public String getListings(boolean typeApart, boolean typeBase, 
    boolean typeAttach, boolean typeDetach, boolean typeTown, 
    boolean typeCondo, String beds, String baths, String furnished, 
    boolean quadNE, boolean quadNW, boolean quadSE, boolean quadSW) {
        
        return client.getListings(typeApart, typeBase, typeAttach, 
            typeDetach, typeTown, typeCondo, beds, baths, furnished, 
            quadNE, quadNW, quadSE, quadSW);
    }

    String loginCommand(String username, String password) {
        return client.login(username, password);
    }
}