package Utils.SQL.QueryStatements.SelectQueries;

import Models.UserPassPair;
import Utils.SQL.JDBC.SQLiteConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringJoiner;

public class SelectFromUsers implements SelectQuery {

    public ResultSet execute(Object o)
    {
        UserPassPair user = (UserPassPair) o;
        String username = user.getUsername();
        String password = user.getPassword();
        ResultSet rs = null;
        try
        {
            Connection dbConn = SQLiteConnector.getInstance().getConnection();
            Statement stmt = dbConn.createStatement();
            StringJoiner sj = new StringJoiner("\n");
            sj.add("SELECT * FROM Users WHERE");
            sj.add(user.getSelectFields());
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
