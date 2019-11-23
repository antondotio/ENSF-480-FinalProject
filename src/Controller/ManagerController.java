package Controller;

import Systems.DatabaseSystem;

public class ManagerController {
    private DatabaseSystem db;
    public ManagerController(DatabaseSystem db) {
        this.db = db;
    }
}
