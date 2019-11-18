package Entity;

public class Property {
    String type;
    int numOfBedrooms;
    double numOfBathrooms;
    boolean furnished;
    String quadrant;
    Address address;
    int propertyID;

    public Property(String type, int numOfBedrooms, double numOfBathrooms, boolean furnished, String quadrant,
            Address address, int propertyID) {
        this.type = type;
        this.numOfBedrooms = numOfBedrooms;
        this.numOfBathrooms = numOfBathrooms;
        this.furnished = furnished;
        this.quadrant = quadrant;
        this.address = address;
        this.propertyID = propertyID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumOfBedrooms() {
        return numOfBedrooms;
    }

    public void setNumOfBedrooms(int numOfBedrooms) {
        this.numOfBedrooms = numOfBedrooms;
    }

    public double getNumOfBathrooms() {
        return numOfBathrooms;
    }

    public void setNumOfBathrooms(double numOfBathrooms) {
        this.numOfBathrooms = numOfBathrooms;
    }

    public boolean isFurnished() {
        return furnished;
    }

    public void setFurnished(boolean furnished) {
        this.furnished = furnished;
    }

    public String getQuadrant() {
        return quadrant;
    }

    public void setQuadrant(String quadrant) {
        this.quadrant = quadrant;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }
}