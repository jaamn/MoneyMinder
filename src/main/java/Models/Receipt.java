package Models;

import Utils.SQL.QueryFactory.SelectQueryFactory;
import Utils.SQL.QueryStatements.SelectQueries.SelectQuery;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.StringJoiner;

public class Receipt {

    private int rid;
    private int sid;
    private String username;
    private Date date;

    public Receipt(int rid, int sid, String username, Date date) {
        this.rid = rid;
        this.sid = sid;
        this.username = username;
        this.date = date;
    }

    public static String fieldsForTableCreation()
    {
        StringJoiner sj = new StringJoiner(",\n\t");
        sj.add("\trid INTEGER ");
        sj.add("sid INTEGER");
        sj.add("username STRING");
        sj.add("date DATE");
        sj.add("FOREIGN KEY(sid) REFERENCES Stores(sid)");
        sj.add("FOREIGN KEY(username) REFERENCES Users(username)");
        sj.add("PRIMARY KEY(rid, username)");

        return sj.toString();
    }

    public StringProperty getDateProp()
    {
        return new SimpleStringProperty(this.date.toString());
    }

    public StringProperty getStoreName()
    {
        StringProperty prop = new SimpleStringProperty();
        SelectQuery query = SelectQueryFactory.getQuery(Tables.Stores);
        try (ResultSet rs = query.execute(this.sid))
        {
            if (rs.next())
            {
                String name = rs.getString("name");
                prop.setValue(name);
                return prop;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String getInsertFields()
    {
        StringJoiner sj = new StringJoiner(",\n");
        sj.add("\t" + this.rid);
        sj.add("\t" + this.sid);
        sj.add("\t'" + this.username+ "'");
        sj.add("\t'" + this.date + "'");

        return sj.toString();
    }

    public static float getSpendingForMonth(User user, String month, String year)
    {
        SelectQuery query = SelectQueryFactory.getQuery(Tables.Receipts);
        try (ResultSet rs = query.execute(user, month, year))
        {
            if (rs.next())
            {
                float sum = rs.getFloat("sumPrice");
                return sum;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static float getSpendingPerCategoryForMonth(User user, Category c, String month, String year)
    {
        SelectQuery query = SelectQueryFactory.getQuery(Tables.Receipts);
        try (ResultSet rs = query.execute(user, c, month, year))
        {
            if (rs.next())
            {
                float sum = rs.getFloat("sumPrice");
                return sum;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
