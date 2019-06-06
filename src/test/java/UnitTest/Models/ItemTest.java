package UnitTest.Models;

import Models.Item;
import Models.Tables;
import Utils.SQL.QueryFactory.InsertQueryFactory;
import Utils.SQL.QueryStatements.InsertQueries.InsertQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;

public class ItemTest {
    @BeforeEach
    void setUp() {
        File database = new File("database.db");
        if(database.exists()) {
            database.delete();
        }
        Main.MainApp M = new Main.MainApp();
        M.initDB();
    }

    @Test
    void insertIntoDB(){
        Item item = new Item(1, 1, "IceCream", (float)5.0, 1);
        InsertQuery insert = InsertQueryFactory.getQuery(Tables.Items);
        Assertions.assertTrue(insert.execute(item));
    }

    @Test
    void getItemsForUser(){

    }

    @Test
    void getCategoryName(){

    }

    @Test
    void getSumPriceForCategory(){

    }

    @Test
    void getMinPriceForCategoryForMonth() {

    }

    @Test
    void getMaxPriceForCateogoryForMonth() {

    }


    @AfterEach
    void tearDown(){
        File database = new File("database.db");
        if(database.exists()) {
            database.delete();
        }
    }

    @Test
    void fieldsForTableCreation() {
    }

    @Test
    void insertIntoDB1() {
    }

    @Test
    void getSumPriceForCategory1() {
    }

    @Test
    void getMinPriceForCategoryForMonth1() {
    }

    @Test
    void getMaxPriceForCategoryForMonth() {
    }
}
