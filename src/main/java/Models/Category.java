package Models;

import java.util.StringJoiner;

public class Category {

    private int cid;
    private String name;

    public static String fieldsForTableCreation()
    {
        StringJoiner sj = new StringJoiner(",\n");
        sj.add("\tcid INTEGER");
        sj.add("\tname TEXT");

        return sj.toString();
    }
}
