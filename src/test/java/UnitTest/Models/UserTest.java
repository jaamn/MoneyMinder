package UnitTest.Models;

import Models.User;
import Utils.PasswordHash;
import Utils.SQL.JDBC.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    String username = "User1";
    String firstname = "First1";
    String lastname = "Last1";
    String password = "Pass1";
    User u = new User(username, firstname, lastname, password);

    @Test
    void getUsername() {
        Assertions.assertEquals("User1", u.getUsername());
    }

    @Test
    void getFirstName() {
        Assertions.assertEquals("First1", u.getFirstName());
    }

    @Test
    void getInsertFields() {
        Assertions.assertEquals(
                "\t'" + username + "'" + ",\n" +
                "\t'" + firstname + "'" + ",\n" +
                "\t'" + lastname + "'" + ",\n" +
                "\t'" + PasswordHash.get_SHA_256_SecurePassword(password, Constants.salt) + "'", u.getInsertFields());
    }

    @Test
    void fieldsForTableCreation() {
        Assertions.assertEquals(
                "\tusername TEXT PRIMARY KEY"
                + ",\n\t" + "firstname TEXT"
                + ",\n\t" + "lastname TEXT"
                + ",\n\t" + "password TEXT", u.fieldsForTableCreation());
    }
}