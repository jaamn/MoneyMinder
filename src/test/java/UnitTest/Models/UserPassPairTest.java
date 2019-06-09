package UnitTest.Models;

import Models.UserPassPair;
import Utils.PasswordHash;
import Utils.SQL.JDBC.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserPassPairTest {

    @Test
    void getUsername() {
        UserPassPair userPassPair = new UserPassPair("test", "1234");
        Assertions.assertEquals("test", userPassPair.getUsername());
    }

    @Test
    void getPassword() {
        UserPassPair userPassPair = new UserPassPair("test", "1234");
        Assertions.assertEquals("1234", userPassPair.getPassword());
    }

    @Test
    void getSelectFields() {
        UserPassPair userPassPair = new UserPassPair("test", "1234");
        String result = userPassPair.getSelectFields();
        StringBuilder sb = new StringBuilder();
        sb.append("\tusername = " + "'" + "test" + "'");
        sb.append(" AND ");
        sb.append("password = " + "'" + PasswordHash.get_SHA_256_SecurePassword("1234", Constants.salt) + "'");
        Assertions.assertEquals(result, sb.toString());
    }
}