package IntegrationTest.Models;

import Main.MainApp;
import Models.Store;
import Models.Tables;
import Utils.SQL.QueryFactory.InsertQueryFactory;
import Utils.SQL.QueryFactory.SelectQueryFactory;
import Utils.SQL.QueryStatements.InsertQueries.InsertQuery;
import Utils.SQL.QueryStatements.SelectQueries.SelectQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.applet.Main;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class StoreIT {

    @BeforeEach
    void setUp() {
//        File database = new File("database.db");
//        MainApp main = new MainApp();
//        if(database.exists()){
//            main.deleteTables();
//        }
//        main.initDB();
    }

    @AfterEach
    void tearDown() {
//        MainApp main = new MainApp();
//        main.deleteTables();
    }

    @Test
    void fieldsForTableCreation() {
    }

    @Test
    void getIdFromName() {
//        String storeName = "walmart";
//        String storeName2 = "target";
//        SelectQuery selectQuery = SelectQueryFactory.getQuery(Tables.Stores);
//        Assertions.assertNotNull(selectQuery, "select query object has been created to select store");
//        InsertQuery insertQuery = InsertQueryFactory.getQuery(Tables.Stores);
//        insertQuery.execute(storeName);
//
//        int result = Store.getIdFromName(storeName);
//        Assertions.assertEquals(1, result);
//
//        int result2 = Store.getIdFromName(storeName2);
//        Assertions.assertEquals(2, result2);
    }
}