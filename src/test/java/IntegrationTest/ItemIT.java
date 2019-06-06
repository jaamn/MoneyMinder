package IntegrationTest;

import Models.*;
import Utils.SQL.QueryFactory.InsertQueryFactory;
import Utils.SQL.QueryFactory.SelectQueryFactory;
import Utils.SQL.QueryStatements.InsertQueries.InsertQuery;
import Utils.SQL.QueryStatements.SelectQueries.SelectQuery;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.ResultSet;

class ItemIT {

    @BeforeEach
    void setUp() {
        File database = new File("database.db");
        if(database.exists()) {
            database.delete();
        }
        Main.MainApp M = new Main.MainApp();
        M.initDB();
    }

    @AfterEach
    void tearDown() throws Exception{
        Path fileToDeletePath = Paths.get("database.db");
        Files.delete(fileToDeletePath);
    }

    //integration
    @Test
    void insertIntoDB(){
        Item item = new Item(1, 1, "IceCream", (float)5.0, 1);
        Item item2 = new Item();
        InsertQuery insert = InsertQueryFactory.getQuery(Tables.Items);
        Assertions.assertTrue(insert.execute(item));
        Assertions.assertTrue(insert.execute(item2));
    }


    @Test
    void fieldsForTableCreation() {
    }

    @Test
    void getItemsForUser(){
        User user = new User("test", "test", "test", "1111");
        Item item = new Item(1, 1, "IceCream", (float)5.0, 1);
        InsertQuery insertItem = InsertQueryFactory.getQuery(Tables.Items);
        insertItem.execute(item);
        SelectQuery selectQuery = SelectQueryFactory.getQuery(Tables.Items);
        ObservableList<ItemReceiptPair> items = FXCollections.observableArrayList();
        Assertions.assertNotNull(selectQuery, "select query object for item is created");
        Assertions.assertNotNull(items, "items list to insert is created");
        Assertions.assertNotNull(Item.getItemsForUser(user), "getItemsForUser returns list object");
    }

    @Test
    void getCategoryName(){
        Item item = new Item(1, 1, "IceCream", (float)5.0, 1);
        SelectQuery query = SelectQueryFactory.getQuery(Tables.Categories);
        StringProperty prop = item.getCategoryName();
        ResultSet rs = query.execute(item.getCid());
        Assertions.assertEquals("Groceries", prop.get(), "String prop object is created and correctly returns category name");
        System.out.println(prop.toString());
        Assertions.assertNotNull(rs, "result set from db is created");
    }

//    @Test
//    void getSumPriceForCategory(){
//        Item item = new Item(1, 1, "Cookie", (float)5.0, 1);
//        User user = new User("Test", "test", "Test", "12345678");
//        Category c = new Category(1, "Groceries");
//        String str = "2019-06-06";
//        Date date = Date.valueOf(str);
//        Receipt receipt = new Receipt(1, 1, "Test", date);
//        InsertQuery insertItem = InsertQueryFactory.getQuery(Tables.Items);
//        insertItem.execute(item);
//        InsertQuery insertReceipt = InsertQueryFactory.getQuery(Tables.Receipts);
//        insertReceipt.execute(receipt);
//        float priceSum = Item.getSumPriceForCategory(user, c);
//        Assertions.assertEquals((float)5.0, priceSum);
//    }

    @Test
    void getMinPriceForCategoryForMonth() {
        Item item = new Item(1, 1, "IceCream", (float)5.0, 1);
        User user = new User("Test", "test", "Test", "12345678");
        Category c = new Category(1, "Groceries");
        String str = "2019-06-06";
        Date date = Date.valueOf(str);
        Receipt receipt = new Receipt(1, 1, "Test", date);
        InsertQuery insertItem = InsertQueryFactory.getQuery(Tables.Items);
        insertItem.execute(item);
        InsertQuery insertReceipt = InsertQueryFactory.getQuery(Tables.Receipts);
        insertReceipt.execute(receipt);
        CategoryPricePair categoryPricePair = Item.getMinPriceForCategoryForMonth(user, c, "06", "2019");
        Assertions.assertNotNull(categoryPricePair);
        Assertions.assertEquals(5.0, categoryPricePair.getPrice());
    }

    @Test
    void getMaxPriceForCateogoryForMonth() {
        Item item = new Item(1, 1, "IceCream", (float)5.0, 1);
        Item item2 = new Item(1, 1, "Cereal", (float)15.0, 1);
        User user = new User("Test", "test", "Test", "12345678");
        Category c = new Category(1, "Groceries");
        String str = "2019-06-06";
        Date date = Date.valueOf(str);
        Receipt receipt = new Receipt(1, 1, "Test", date);
        InsertQuery insertItem = InsertQueryFactory.getQuery(Tables.Items);
        insertItem.execute(item);
        insertItem.execute(item2);
        InsertQuery insertReceipt = InsertQueryFactory.getQuery(Tables.Receipts);
        insertReceipt.execute(receipt);
        CategoryPricePair categoryPricePair = Item.getMaxPriceForCategoryForMonth(user, c, "06", "2019");
        Assertions.assertNotNull(categoryPricePair);
        Assertions.assertEquals(15.0, categoryPricePair.getPrice());
    }
}