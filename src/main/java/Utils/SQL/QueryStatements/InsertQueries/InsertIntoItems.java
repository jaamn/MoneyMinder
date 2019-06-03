package Utils.SQL.QueryStatements.InsertQueries;

import Models.Item;
import Utils.SQL.JDBC.SQLiteConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringJoiner;

public class InsertIntoItems implements InsertQuery {
    public boolean execute(Object o)
    {
        Item i = (Item) o;
        try {
            Connection dbConn = SQLiteConnector.getInstance().getConnection();
            Statement stmt = dbConn.createStatement();
            StringJoiner sj = new StringJoiner("\n");
            sj.add("INSERT INTO Items(rid, cid, quantity) VALUES(");
            sj.add(i.getRid() + ",");
            sj.add(i.getCid() + ",");
            sj.add(Integer.toString(i.getQuantity()));
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
