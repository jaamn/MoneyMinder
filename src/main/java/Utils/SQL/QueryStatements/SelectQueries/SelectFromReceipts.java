package Utils.SQL.QueryStatements.SelectQueries;

import Models.Category;
import Models.User;
import Utils.SQL.JDBC.SQLiteConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringJoiner;

public class SelectFromReceipts implements SelectQuery {

    public ResultSet execute(Object o, Object c, Object m, Object y)
    {
        User user = (User) o;
        Category category = (Category) c;
        String month = (String) m;
        String year = (String) y;

        ResultSet rs = null;
        try
        {
            Connection dbConn = SQLiteConnector.getInstance().getConnection();
            Statement stmt = dbConn.createStatement();
            StringJoiner sj = new StringJoiner("\n");
            sj.add("SELECT SUM(price) as sumPrice FROM Items NATURAL JOIN Receipts WHERE username = ");
            sj.add("'" + user.getUsername() + "'");
            sj.add("AND cid = " + category.getCid());
            sj.add("AND strftime('%Y', date) = '" + year + "'");
            sj.add("AND strftime('%m', date) = '" + month + "'");

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
        User user = (User) o;
        String month = (String) m;
        String year = (String) y;

        ResultSet rs = null;
        try
        {
            Connection dbConn = SQLiteConnector.getInstance().getConnection();
            Statement stmt = dbConn.createStatement();
            StringJoiner sj = new StringJoiner("\n");
            sj.add("SELECT SUM(price) as sumPrice FROM Items NATURAL JOIN Receipts WHERE username = ");
            sj.add("'" + user.getUsername() + "'");
            sj.add("AND strftime('%Y', date) = '" + year + "'");
            sj.add("AND strftime('%m', date) = '" + month + "'");

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

    public ResultSet execute(Object o, Object m)
    {
        return null;
    }

    public ResultSet execute(Object o)
    {
        return null;
    }

    public ResultSet execute()
    {
        return null;
    }
}
