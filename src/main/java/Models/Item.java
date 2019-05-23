package Models;

import java.util.StringJoiner;

public class Item {

    private int iid;
    private int rid;
    private int cid;
    private int quantity;

    public static String fieldsForTableCreation()
    {
        StringJoiner sj = new StringJoiner(",\n");
        sj.add("iid INTEGER PRIMARY KEY");
        sj.add("rid INTEGER");
        sj.add("cid INTEGER");
        sj.add("quantity INTEGER");
        sj.add("FOREIGN KEY(rid) REFERENCES Receipts(rid)");
        sj.add("FOREIGN KEY(cid) REFERENCES Categories(cid)");

        return sj.toString();
    }
}
