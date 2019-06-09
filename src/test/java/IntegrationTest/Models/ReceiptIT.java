package IntegrationTest.Models;

import Models.*;
import Utils.SQL.QueryFactory.InsertQueryFactory;
import Utils.SQL.QueryFactory.SelectQueryFactory;
import Utils.SQL.QueryStatements.InsertQueries.InsertQuery;
import Utils.SQL.QueryStatements.SelectQueries.SelectQuery;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.*;

import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptIT {
    int rid = Integer.parseInt("1");
    int sid = Store.getIdFromName("Village Market");
    User u = new User("Test1","Test1","Test1","Test1");
    Receipt r = new Receipt(rid, sid, "Test1", Date.valueOf("2019-06-06"));
    Receipt r2 = new Receipt(2, 2, "Test2", Date.valueOf("2019-06-06"));
    Item i = new Item(1, 1, "TestItem", (float)5.0, 1);
        @BeforeEach
        void setUp() {
            File database = new File("database.db");
            Main.MainApp M = new Main.MainApp();
            if(database.exists()) {
                M.deleteTables();
            }
            M.initDB();
        }

        @BeforeEach
        void tearDown() {
            Main.MainApp M = new Main.MainApp();
            M.deleteTables();
        }

        @Test
        void getStoreName() {
            InsertQuery insertS = InsertQueryFactory.getQuery(Tables.Stores);
            insertS.execute("Village Market");

            InsertQuery insert = InsertQueryFactory.getQuery(Tables.Users);
            insert.execute(u);

            InsertQuery insertR = InsertQueryFactory.getQuery(Tables.Receipts);
            insertR.execute(r);

            Assertions.assertEquals("Village Market", r.getStoreName().getValue());
        }

        @Test
        void insertIntoDB() {
            InsertQuery insert = InsertQueryFactory.getQuery(Tables.Receipts);
            Assertions.assertTrue(insert.execute(r2));
        }

        @Test
        void getSpendingForMonth() {
            InsertQuery insertS = InsertQueryFactory.getQuery(Tables.Stores);
            insertS.execute("Village Market");

            InsertQuery insert = InsertQueryFactory.getQuery(Tables.Users);
            insert.execute(u);

            InsertQuery insertR = InsertQueryFactory.getQuery(Tables.Receipts);
            insertR.execute(r);

            InsertQuery insertItem = InsertQueryFactory.getQuery(Tables.Items);
            insertItem.execute(i);

            Assertions.assertEquals((float)5.0, r.getSpendingForMonth(u,"06", "2019"));
        }

//    @Test
//    void getSpendingPerCategoryForMonth() {
//
//    }
    }