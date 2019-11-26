package Controller;

import Entity.Listing;

public interface Subject {
    public void register(Observer o);

    public void unregister(Observer o);

    public void notifyAllObservers(Listing l);
}
