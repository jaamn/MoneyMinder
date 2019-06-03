package Models;

import Utils.PasswordHash;
import Utils.SQL.JDBC.Constants;
import Utils.SQL.QueryFactory.InsertQueryFactory;
import Utils.SQL.QueryFactory.SelectQueryFactory;
import Utils.SQL.QueryStatements.InsertQueries.InsertQuery;
import Utils.SQL.QueryStatements.SelectQueries.SelectQuery;

import java.sql.ResultSet;
import java.util.StringJoiner;

public class User {

    private String username;
    private String firstname;
    private String lastname;
    private String password;

    public User(String username, String firstname, String lastname, String password)
    {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName()
    {
        return this.firstname;
    }

    public static User verifyUser(String username, String password)
    {
        SelectQuery query = SelectQueryFactory.getQuery(Tables.Users);
        try (ResultSet rs = query.execute(new UserPassPair(username, password)))
        {
            if (rs.next())
            {
                String sqlUser = rs.getString("username");
                String sqlPass = rs.getString("password");
                String sqlFirst = rs.getString("firstname");
                String sqlLast = rs.getString("lastname");
                return new User(sqlUser, sqlFirst, sqlLast, sqlPass);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean registerUser()
    {
        InsertQuery insert = InsertQueryFactory.getQuery(Tables.Users);
        return insert.execute(this);
    }

    public String getInsertFields()
    {
        StringJoiner sj = new StringJoiner(",\n");
        sj.add("\t'" + this.username + "'");
        sj.add("\t'" + this.firstname + "'");
        sj.add("\t'" + this.lastname + "'");
        sj.add("\t'" + PasswordHash.get_SHA_256_SecurePassword(this.password, Constants.salt) + "'");

        return sj.toString();
    }

    public static String fieldsForTableCreation()
    {
        StringJoiner sj = new StringJoiner(",\n\t");
        sj.add("\tusername TEXT PRIMARY KEY");
        sj.add("firstname TEXT");
        sj.add("lastname TEXT");
        sj.add("password TEXT");

        return sj.toString();
    }
}
