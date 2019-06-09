package UnitTest.Models;

import Models.Item;
import Models.ItemReceiptPair;
import Models.Receipt;
import Models.Store;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;

class ItemReceiptPairTest {
    Item i = new Item(1, 1, "IceCream", (float)5.0, 1);
    int rid = Integer.parseInt("1");
    int sid = Store.getIdFromName("Village Market");
    Receipt r = new Receipt(rid, sid, "Test1", Date.valueOf("2019-06-06"));
    ItemReceiptPair irp = new ItemReceiptPair(i, r);

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
        Assertions.assertEquals("Village Market", irp.getReceipt().getStoreName().getValue());
        Assertions.assertEquals("2019-06-06", irp.getReceipt().getDateProp().getValue());
        Assertions.assertEquals(
                "\t" + rid + ",\n"
                + "\t" + sid + ",\n"
                + "\t'" +"Test1" + "',\n"
                + "\t'" + Date.valueOf("2019-06-06") + "'", irp.getReceipt().getInsertFields());
    }
}