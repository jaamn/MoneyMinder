package Utils.SQL.QueryStatements.InsertQueries;

import Utils.SQL.JDBC.SQLiteConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringJoiner;

public class InsertIntoStores implements InsertQuery {
    public boolean execute(Object o)
    {
        String name = (String) o;
        try {
            Connection dbConn = SQLiteConnector.getInstance().getConnection();
            Statement stmt = dbConn.createStatement();
            StringJoiner sj = new StringJoiner("\n");
            sj.add("INSERT INTO Stores(name) VALUES(");
            sj.add("'" + name + "'");
            sj.add(");");
            String insert = sj.toString();
            System.out.println(insert);
            stmt.executeUpdate(insert);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }
}
