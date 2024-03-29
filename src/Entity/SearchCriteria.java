package Entity;

public class SearchCriteria {
    String type;
    Integer numOfBedrooms;
    Double numOfBathrooms;
    Boolean furnished;
    String quadrant;
    int accountId;
    int id;

    public SearchCriteria(String type, Integer numOfBedrooms, Double numOfBathrooms, Boolean furnished, String quadrant, int accountId) {
        this.type = type;
        this.numOfBedrooms = numOfBedrooms;
        this.numOfBathrooms = numOfBathrooms;
        this.furnished = furnished;
        this.quadrant = quadrant;
        this.accountId = accountId;
    }

    public SearchCriteria(int id, String type, Integer numOfBedrooms, Double numOfBathrooms, Boolean furnished, String quadrant, int accountId) {
        this.id = id;
        this.type = type;
        this.numOfBedrooms = numOfBedrooms;
        this.numOfBathrooms = numOfBathrooms;
        this.furnished = furnished;
        this.quadrant = quadrant;
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumOfBedrooms() {
        return numOfBedrooms;
    }

    public void setNumOfBedrooms(int numOfBedrooms) {
        this.numOfBedrooms = numOfBedrooms;
    }

    public Double getNumOfBathrooms() {
        return numOfBathrooms;
    }

    public void setNumOfBathrooms(double numOfBathrooms) {
        this.numOfBathrooms = numOfBathrooms;
    }

    public Boolean isFurnished() {
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

    public int getAccountId() {
        return accountId;
    }

    public int getId() {
        return id;
    }
}