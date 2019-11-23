package Entity;

public abstract class Account {
    Name name;
    int accountID;
    String email;

    public Account(Name name, int accountID, String email) {
        this.name = name;
        this.accountID = accountID;
        this.email = email;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public int getAccountID() {
        return accountID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}