package Utils.SQL.QueryStatements.CreateTableQueries;

import Models.Receipt;
import Utils.SQL.JDBC.SQLiteConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringJoiner;

public class CreateReceiptsTable implements CreateTableQuery {

    public Boolean execute()
    {
        try {
            Connection dbConn = SQLiteConnector.getInstance().getConnection();
            Statement stmt = dbConn.createStatement();
            StringJoiner sj = new StringJoiner("\n");
            sj.add("CREATE TABLE IF NOT EXISTS Receipts(");
            sj.add(Receipt.fieldsForTableCreation());
            sj.add(");");

            String query = sj.toString();
            System.out.println(query);
            stmt.execute(query);
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

}
