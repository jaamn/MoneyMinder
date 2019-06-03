package Models;

import Utils.SQL.QueryFactory.SelectQueryFactory;
import Utils.SQL.QueryStatements.SelectQueries.SelectQuery;

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
        sj.add("\trid INTEGER PRIMARY KEY");
        sj.add("sid INTEGER");
        sj.add("username STRING");
        sj.add("date DATE");
        sj.add("FOREIGN KEY(sid) REFERENCES Stores(sid)");
        sj.add("FOREIGN KEY(username) REFERENCES Users(username)");

        return sj.toString();
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
}
