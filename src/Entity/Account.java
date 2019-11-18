package Entity;

public class Account {
    Name name;
    String password;
    int accountID;
    String email;

    public Account(Name name, String password, int accountID, String email) {
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}