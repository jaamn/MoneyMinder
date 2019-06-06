package Utils.SQL.JDBC;

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class SQLiteConnector {

    private Connection connection;
    private static SQLiteConnector connector;

    public static SQLiteConnector getInstance() throws ClassNotFoundException, SQLException {
        if (connector == null)
        {
            connector = new SQLiteConnector();
        }
        return connector;
    }

    private SQLiteConnector() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = establishConnection();
    }

    private Connection establishConnection() throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.setReadOnly(false);
        return DriverManager.getConnection(Constants.dbUrl, config.toProperties());
    }

    public static void main (String[] args) throws ClassNotFoundException, SQLException {
        SQLiteConnector c = SQLiteConnector.getInstance();
        c.establishConnection();
    }

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = establishConnection();
        }
        return connection;
    }
}
