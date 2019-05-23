package Utils.SQL.QueryStatements.InsertQueries;

import Models.User;
import Utils.SQL.JDBC.SQLiteConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringJoiner;

import static Models.Tables.Users;

public class InsertIntoUsers implements InsertQuery {

    public boolean execute(Object o)
    {
        User user = (User) o;
        try {
            Connection dbConn = SQLiteConnector.getInstance().getConnection();
            Statement stmt = dbConn.createStatement();
            StringJoiner sj = new StringJoiner("\n");
            sj.add("INSERT INTO Users VALUES(");
            sj.add(user.getInsertFields());
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
