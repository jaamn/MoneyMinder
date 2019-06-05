package Utils.SQL.QueryStatements.SelectQueries;

import Utils.SQL.JDBC.SQLiteConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringJoiner;

public class SelectFromStores implements SelectQuery {

    public ResultSet execute(Object o)
    {
        String cond;
        if (o instanceof String)
        {
            String name = (String) o;
            cond = "name = '" + name + "'";
        }
        else
        {
            int sid = (int) o;
            cond = "sid = " + sid;
        }

        ResultSet rs = null;
        try
        {
            Connection dbConn = SQLiteConnector.getInstance().getConnection();
            Statement stmt = dbConn.createStatement();
            StringJoiner sj = new StringJoiner("\n");
            sj.add("SELECT * FROM Stores WHERE " + cond);
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

    public ResultSet execute()
    {
        ResultSet rs = null;
        try
        {
            Connection dbConn = SQLiteConnector.getInstance().getConnection();
            Statement stmt = dbConn.createStatement();
            StringJoiner sj = new StringJoiner("\n");
            sj.add("SELECT * FROM Stores");
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

    public ResultSet execute(Object c, Object o)
    {
        return null;
    }

    public ResultSet execute(Object o, Object c, Object m, Object y)
    {
        return null;
    }
}
