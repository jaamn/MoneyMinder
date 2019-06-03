package Models;

import Utils.SQL.QueryFactory.InsertQueryFactory;
import Utils.SQL.QueryFactory.SelectQueryFactory;
import Utils.SQL.QueryStatements.InsertQueries.InsertQuery;
import Utils.SQL.QueryStatements.SelectQueries.SelectQuery;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
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

    static public int getIdFromName(String name)
    {
        SelectQuery query = SelectQueryFactory.getQuery(Tables.Stores);
        try (ResultSet rs = query.execute(name))
        {
            if (rs.next())
            {
                String sid = rs.getString("sid");
                return Integer.parseInt(sid);
            }
            else
            {
                InsertQuery insert = InsertQueryFactory.getQuery(Tables.Stores);
                insert.execute(name);
                return getIdFromName(name);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return new Integer(null); // TODO bs
        }
    }
}
