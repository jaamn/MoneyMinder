package Models;

import Utils.PasswordHash;
import Utils.SQL.JDBC.Constants;

public class UserPassPair {
    private String username;
    private String password;

    public UserPassPair(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSelectFields()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\tusername = " + "'" + username + "'");
        sb.append(" AND ");
        sb.append("password = " + "'" + PasswordHash.get_SHA_256_SecurePassword(password, Constants.salt) + "'");

        return sb.toString();
    }
}
