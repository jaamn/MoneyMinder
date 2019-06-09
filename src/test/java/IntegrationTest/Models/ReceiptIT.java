package IntegrationTest.Models;

import Models.Receipt;
import Models.Store;
import Models.Tables;
import Models.User;
import Utils.SQL.QueryFactory.InsertQueryFactory;
import Utils.SQL.QueryFactory.SelectQueryFactory;
import Utils.SQL.QueryStatements.InsertQueries.InsertQuery;
import Utils.SQL.QueryStatements.SelectQueries.SelectQuery;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {

    @BeforeEach
    void setUp() {
        File database = new File("database.db");
        Main.MainApp M = new Main.MainApp();
        if(database.exists()) {
            M.deleteTables();
        }
        M.initDB();
    }

    @AfterEach
    void tearDown() {
        Main.MainApp M = new Main.MainApp();
        M.deleteTables();
    }


    @Test
    void fieldsForTableCreation() {
    }

    @Test
    void getDateProp() {
    }

    @Test
    void getStoreName() {
        int rid = Integer.parseInt("1");
        int sid = Store.getIdFromName("Village Market");

        InsertQuery insertS = InsertQueryFactory.getQuery(Tables.Stores);
        insertS.execute("Village Market");

        User u = new User("Test1","Test1","Test1","Test1");
        InsertQuery insert = InsertQueryFactory.getQuery(Tables.Users);
        insert.execute(u);

        Receipt r = new Receipt(rid, sid, "Test1", Date.valueOf("2019-06-06"));
        InsertQuery insertR = InsertQueryFactory.getQuery(Tables.Receipts);
        insertR.execute(r);

        Assertions.assertEquals("Village Market", r.getStoreName().getValue());
    }

    @Test
    void getInsertFields() {

    }

    @Test
    void insertIntoDB() {
        Receipt r = new Receipt(2, 2, "Test2", Date.valueOf("2019-06-06"));
        InsertQuery insert = InsertQueryFactory.getQuery(Tables.Receipts);
        Assertions.assertTrue(insert.execute(r));
    }

    @Test
    void getSpendingForMonth() {
    }

    @Test
    void getSpendingPerCategoryForMonth() {
    }
}