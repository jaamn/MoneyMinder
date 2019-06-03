package Utils.SQL.QueryStatements.InsertQueries;

import Models.Receipt;
import Models.User;
import Utils.SQL.JDBC.SQLiteConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringJoiner;

public class InsertIntoReceipts implements InsertQuery {

    public boolean execute(Object o)
    {
        Receipt r = (Receipt) o;
        try {
            Connection dbConn = SQLiteConnector.getInstance().getConnection();
            Statement stmt = dbConn.createStatement();
            StringJoiner sj = new StringJoiner("\n");
            sj.add("INSERT INTO Receipts VALUES(");
            sj.add(r.getInsertFields());
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
