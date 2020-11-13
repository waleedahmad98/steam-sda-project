package dal;

import bl.interface_db;

public class DAL {

    private static interface_db db = null; // uses the instance of the interface. it is assigned either local or mongo db instance.

    public static void setInstance(interface_db db){
        DAL.db = db;
    }

    public static interface_db getInstance() {
        return db;
    }

}
