package Utils.SQL.QueryStatements.SelectQueries;

import Models.Category;
import Models.User;
import Utils.SQL.JDBC.SQLiteConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringJoiner;

public class SelectAggregateFromItems implements SelectQuery {

    String op;

    public SelectAggregateFromItems(String agg)
    {
        this.op = agg;
    }

    public ResultSet execute(Object c, Object o)
    {
        User user = (User) o;
        Category category = (Category) c;
        ResultSet rs = null;
        try
        {
            Connection dbConn = SQLiteConnector.getInstance().getConnection();
            Statement stmt = dbConn.createStatement();
            StringJoiner sj = new StringJoiner("\n");
            sj.add("SELECT " + op + "(price) as aggPrice FROM Items INNER JOIN Receipts WHERE username = ");
            sj.add("'" + user.getUsername() + "'");
            sj.add("AND cid = " + category.getCid());
            sj.add(";");
            String select = sj.toString();
            System.out.println(select);
            rs = stmt.executeQuery(select);
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return rs;
    }

    public ResultSet execute(Object o, Object m, Object y)
    {
        return null;
    }

    public ResultSet execute()
    {
        return null;
    }
    public ResultSet execute(Object o)
    {
        return null;
    }


    public ResultSet execute(Object o, Object c, Object m, Object y)
    {
        return null;
    }
}
