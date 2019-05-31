package Models;

import java.sql.Date;
import java.util.StringJoiner;

public class Receipt {

    private int rid;
    private int sid;
    private int username;
    private Date date;

    public static String fieldsForTableCreation()
    {
        StringJoiner sj = new StringJoiner(",\n\t");
        sj.add("\trid INTEGER PRIMARY KEY");
        sj.add("sid INTEGER");
        sj.add("username STRING");
        sj.add("date DATE");
        sj.add("FOREIGN KEY(sid) REFERENCES Stores(sid)");
        sj.add("FOREIGN KEY(username) REFERENCES Users(username)");

        return sj.toString();
    }
}
