package Utils.SQL.QueryStatements.SelectQueries;

import Models.Category;
import Models.User;
import Models.UserPassPair;
import Utils.SQL.JDBC.SQLiteConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringJoiner;

public class SelectFromItems implements SelectQuery {

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
            sj.add("SELECT SUM(price) as sumPrice FROM Items INNER JOIN Receipts WHERE username = ");
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

    public ResultSet execute(Object o)
    {
        User user = (User) o;
        ResultSet rs = null;
        try
        {
            Connection dbConn = SQLiteConnector.getInstance().getConnection();
            Statement stmt = dbConn.createStatement();
            StringJoiner sj = new StringJoiner("\n");
            sj.add("SELECT * FROM Items INNER JOIN Receipts WHERE username = ");
            sj.add("'" + user.getUsername() + "'");
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
            sj.add("SELECT * FROM Items");
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

}
