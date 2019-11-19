package Controller;

public class Listener {
    
    private Client client;
    private static Listener listener = null;

    private Listener()
    {
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

    public String actionPerformed(String s)
    {
        String[] split = s.split("-", 2);
        if(s.equals("basic input"))
        {
            //return client action function call
        }
        else if(split[0].equals("other command involving input"))
        {
            //return client action function call
        }
        return "";
    }
}