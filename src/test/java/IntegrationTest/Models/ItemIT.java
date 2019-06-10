package IntegrationTest.Models;

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
import java.sql.Date;
import java.sql.ResultSet;

class ItemIT {

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
    void tearDown(){
//        Main.MainApp M = new Main.MainApp();
//        M.deleteTables();
    }

    //integration
    @Test
    void insertIntoDB(){
//        Item item = new Item(1, 1, "IceCream", (float)5.0, 1);
//        item.insertIntoDB();
    }

    @Test
    void fieldsForTableCreation() {
    }

    @Test
    void getItemsForUser() {
//        String str = "2019-06-01";
//        Date date = Date.valueOf(str);
//        User user = new User("test", "test", "test", "1111");
//        InsertQuery insertUser = InsertQueryFactory.getQuery(Tables.Users);
//        insertUser.execute(user);
//
//        Receipt receipt = new Receipt(1, 1, "test", date);
//        InsertQuery insertReceipt = InsertQueryFactory.getQuery(Tables.Receipts);
//        insertReceipt.execute(receipt);
//
//        Item item = new Item(1, 1, "IceCream", (float)5.0, 1);
//        Item item2 = new Item(1, 1, "Snacks", (float)10.0, 2);
//        InsertQuery insertItem = InsertQueryFactory.getQuery(Tables.Items);
//        insertItem.execute(item);
//        insertItem.execute(item2);
//
//        SelectQuery selectQuery = SelectQueryFactory.getQuery(Tables.Items);
//        Assertions.assertNotNull(selectQuery, "select query object for item is created");
//
//        ObservableList<ItemReceiptPair> items = FXCollections.observableArrayList();
//        Assertions.assertNotNull(items, "items list to insert is created");
//
//        ResultSet rs = selectQuery.execute(user);
//        Assertions.assertNotNull(rs, "can select query from user");
//        Assertions.assertNotNull(Item.getItemsForUser(user), "getItemsForUser returns list object");
    }

    @Test
    void getCategoryName(){
//        Item item = new Item(1, 1, "IceCream", (float)5.0, 1);
//        SelectQuery query = SelectQueryFactory.getQuery(Tables.Categories);
//        StringProperty prop = item.getCategoryName();
//        ResultSet rs = query.execute(item.getCid());
//        Assertions.assertEquals("Groceries", prop.get(), "String prop object is created and correctly returns category name");
//        System.out.println(prop.toString());
//        Assertions.assertNotNull(rs, "result set from db is created");
    }

    @Test
    void getSumPriceForCategory(){
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
    }

    @Test
    void getMinPriceForCategoryForMonth() {
//        Item item = new Item(1, 1, "IceCream", (float)5.0, 1);
//        User user = new User("Test", "test", "Test", "12345678");
//        Category c = new Category(1, "Groceries");
//        String str = "2019-06-06";
//        Date date = Date.valueOf(str);
//        Receipt receipt = new Receipt(1, 1, "Test", date);
//        InsertQuery insertItem = InsertQueryFactory.getQuery(Tables.Items);
//        insertItem.execute(item);
//        InsertQuery insertReceipt = InsertQueryFactory.getQuery(Tables.Receipts);
//        insertReceipt.execute(receipt);
//        CategoryPricePair categoryPricePair = Item.getMinPriceForCategoryForMonth(user, c, "06", "2019");
//        Assertions.assertNotNull(categoryPricePair);
//        Assertions.assertEquals(5.0, categoryPricePair.getPrice());
    }

    @Test
    void getMaxPriceForCateogoryForMonth() {
//        Item item = new Item(1, 1, "IceCream", (float)5.0, 1);
//        Item item2 = new Item(1, 1, "Cereal", (float)15.0, 1);
//        User user = new User("Test", "test", "Test", "12345678");
//        Category c = new Category(1, "Groceries");
//        String str = "2019-06-06";
//        Date date = Date.valueOf(str);
//        Receipt receipt = new Receipt(1, 1, "Test", date);
//        InsertQuery insertItem = InsertQueryFactory.getQuery(Tables.Items);
//        insertItem.execute(item);
//        insertItem.execute(item2);
//        InsertQuery insertReceipt = InsertQueryFactory.getQuery(Tables.Receipts);
//        insertReceipt.execute(receipt);
//        CategoryPricePair categoryPricePair = Item.getMaxPriceForCategoryForMonth(user, c, "06", "2019");
//        Assertions.assertNotNull(categoryPricePair);
//        Assertions.assertEquals(15.0, categoryPricePair.getPrice());
    }

    @Test
    void getRid() {
    }

    @Test
    void getCid() {
    }

    @Test
    void getQuantity() {
    }

    @Test
    void getName() {
    }

    @Test
    void getPrice() {
    }

    @Test
    void setCid() {
    }

    @Test
    void setQuantity() {
    }

    @Test
    void setRid() {
    }

    @Test
    void setName() {
    }

    @Test
    void setPrice() {
    }

    @Test
    void quantityProperty() {
//        Item item = new Item(1, 1, "IceCream", (float)5.0, 1);
//        StringProperty prop = item.quantityProperty();
//        Assertions.assertEquals("1", prop.get());
    }

    @Test
    void nameProperty() {
//        Item item = new Item(1, 1, "IceCream", (float)5.0, 1);
//        StringProperty prop = item.nameProperty();
//        Assertions.assertEquals("IceCream", prop.get());
    }

    @Test
    void priceProperty() {
//        Item item = new Item(1, 1, "IceCream", (float)5.0, 1);
//        StringProperty prop = item.priceProperty();
//        Assertions.assertEquals("5.0", prop.get());
    }

    @Test
    void ridProperty() {
//        Item item = new Item(1, 1, "IceCream", (float)5.0, 1);
//        StringProperty prop = item.ridProperty();
//        Assertions.assertEquals("1", prop.get());
    }
}