package Models;

import java.util.StringJoiner;

public class Store {

    private int sid;
    private String name;

    public static String fieldsForTableCreation()
    {
        StringJoiner sj = new StringJoiner(",\n\t");
        sj.add("\tsid INTEGER PRIMARY KEY");
        sj.add("name TEXT");

        return sj.toString();
    }
}
