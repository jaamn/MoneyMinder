package IntegrationTest.Models;

import Models.*;
import Utils.SQL.QueryFactory.InsertQueryFactory;
import Utils.SQL.QueryStatements.InsertQueries.InsertQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.Date;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;

class ItemReceiptPairIT {
    Item i = new Item(1, 1, "IceCream", (float)5.0, 1);
    int rid = Integer.parseInt("1");
    int sid = Store.getIdFromName("Village Market");
    Receipt r = new Receipt(rid, sid, "Test1", Date.valueOf("2019-06-06"));
    User u = new User("Test1","Test1","Test1","Test1");
    ItemReceiptPair irp = new ItemReceiptPair(i, r);

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
    void tearDown(){
        Main.MainApp M = new Main.MainApp();
        M.deleteTables();
    }

    @Test
    void getItem() {
        Assertions.assertEquals("IceCream", irp.getItem().getName());
        Assertions.assertEquals(1, irp.getItem().getCid());
        Assertions.assertEquals((float)5.0, irp.getItem().getPrice());
        Assertions.assertEquals(1, irp.getItem().getQuantity());
        Assertions.assertEquals(1, irp.getItem().getRid());
    }

    @Test
    void getReceipt() {
        InsertQuery insertS = InsertQueryFactory.getQuery(Tables.Stores);
        insertS.execute("Village Market");

        InsertQuery insert = InsertQueryFactory.getQuery(Tables.Users);
        insert.execute(u);

        InsertQuery insertR = InsertQueryFactory.getQuery(Tables.Receipts);
        insertR.execute(r);

        Assertions.assertEquals("Village Market", irp.getReceipt().getStoreName().getValue());
        Assertions.assertEquals("2019-06-06", irp.getReceipt().getDateProp().getValue());
        Assertions.assertEquals(
                "\t" + rid + ",\n"
                + "\t" + sid + ",\n"
                + "\t'" +"Test1" + "',\n"
                + "\t'" + Date.valueOf("2019-06-06") + "'", irp.getReceipt().getInsertFields());
    }
}