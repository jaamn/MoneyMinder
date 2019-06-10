package IntegrationTest.Models;

import Models.Category;
import Models.Tables;
import Utils.SQL.QueryFactory.InsertQueryFactory;
import Utils.SQL.QueryStatements.InsertQueries.InsertQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class CategoryIT {

    @BeforeEach
    void setUp() {
//        File database = new File("database.db");
//        if(database.exists()) {
//            database.delete();
//        }
//        Main.MainApp M = new Main.MainApp();
//        M.initDB();
    }

    @AfterEach
    void tearDown() {
//        File database = new File("database.db");
//        if(database.exists()) {
//            database.delete();
//        }
    }

    @Test
    void insertIntoDB() {
        /*  The following Integration Test tests insertIntoDB(),
            - a function in the class Category
            - integration of SQL operation, enum values, factory operation
            - inserts specific category into database
         */
//        Category c = new Category(7, "Gifts");
//        InsertQuery insert = InsertQueryFactory.getQuery(Tables.Categories);
//        Assertions.assertTrue(insert.execute(c));
    }
}