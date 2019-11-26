package Controller;

import Entity.Account;
import Systems.DatabaseSystem;

public class LoginController {
    private static LoginController instance;
    private DatabaseSystem db;

    private LoginController(DatabaseSystem db) {
        this.db = db;
        instance = this;
    }

    public static LoginController getInstance(DatabaseSystem db) {
        if (instance == null) {
            instance = new LoginController(db);
        }
        return instance;
    }

    public String authenticate(String email, String password) {
        return db.authenticate(email, password);
    }

    public boolean signup(String email, String password) {
        return true;
    }
}
