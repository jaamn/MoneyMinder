package UnitTest.Models;

import Models.Receipt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;


class ReceiptTest {
    int rid = 1;
    int sid = 1;
    Receipt r = new Receipt(1, 1, "Test1", Date.valueOf("2019-06-06"));

    @Test
    void fieldsForTableCreation() {
        Assertions.assertEquals(
                "\trid INTEGER " + ",\n"
                + "\t" + "sid INTEGER"+ ",\n"
                + "\t" + "username STRING" + ",\n"
                + "\t" + "date DATE" + ",\n"
                + "\t" + "FOREIGN KEY(sid) REFERENCES Stores(sid)" + ",\n"
                + "\t" + "FOREIGN KEY(username) REFERENCES Users(username)" + ",\n"
                + "\t" + "PRIMARY KEY(rid, username)", r.fieldsForTableCreation());

    }

    @Test
    void getDateProp() {
        Assertions.assertEquals("2019-06-06", r.getDateProp().getValue());
    }

    @Test
    void getInsertFields() {
        Assertions.assertEquals(
                "\t" + rid + ",\n"
                        + "\t" + sid + ",\n"
                        + "\t'" +"Test1" + "',\n"
                        + "\t'" + Date.valueOf("2019-06-06") + "'", r.getInsertFields());
    }
}