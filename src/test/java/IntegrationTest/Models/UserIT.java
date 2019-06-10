package IntegrationTest.Models;

import Models.Tables;
import Models.User;
import Utils.SQL.QueryFactory.InsertQueryFactory;
import Utils.SQL.QueryStatements.InsertQueries.InsertQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class UserIT {
    String username = "User1";
    String firstname = "First1";
    String lastname = "Last1";
    String password = "Pass1";
    User u = new User(username, firstname, lastname, password);
    String username2 = "User2";
    String firstname2 = "First2";
    String lastname2 = "Last2";
    String password2 = "Pass2";
    User u2 = new User(username2, firstname2, lastname2, password2);

    @BeforeEach
    void setUp() {
//        File database = new File("database.db");
//        Main.MainApp M = new Main.MainApp();
//        if(database.exists()) {
//            M.deleteTables();
//        }
//        M.initDB();
    }

    @AfterEach
    void tearDown() {
//        Main.MainApp M = new Main.MainApp();
//        M.deleteTables();
    }

    @Test
    void verifyUser() {
//        InsertQuery insert = InsertQueryFactory.getQuery(Tables.Users);
//        insert.execute(u);
//        insert.execute(u2);
//        User u1 = u.verifyUser(username, password);
//        Assertions.assertEquals(username, u1.getUsername());
//        Assertions.assertEquals(firstname, u1.getFirstName());
    }

    @Test
    void registerUser() {
//        Assertions.assertTrue(u.registerUser());
    }
}