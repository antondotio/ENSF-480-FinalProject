package Entity;

public class SearchCriteria {
    String type;
    int numOfBedrooms;
    double numOfBathrooms;
    boolean furnished;
    String quadrant;

    public SearchCriteria(String type, int numOfBedrooms, double numOfBathrooms, boolean furnished, String quadrant) {
        this.type = type;
        this.numOfBedrooms = numOfBedrooms;
        this.numOfBathrooms = numOfBathrooms;
        this.furnished = furnished;
        this.quadrant = quadrant;
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
}