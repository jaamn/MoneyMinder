package UnitTest.Models;
import Models.Category;
import Models.Tables;
import Utils.SQL.QueryFactory.InsertQueryFactory;
import Utils.SQL.QueryStatements.InsertQueries.InsertQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.util.List;

public class CategoryTest {

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
//        File database = new File((System.getProperty("user.dir") + "\\database.db"));
//        if(database.exists()) {
//            database.delete();
//        }
    }

    /* can't override */
//    @Test
//    void toString() {
//    }

    /* moved to integration */
//    @Test
//    void insertIntoDB() { //Integration
//        Category c = new Category(7, "Gifts");
//        InsertQuery insert = InsertQueryFactory.getQuery(Tables.Categories);
//        Assertions.assertTrue(insert.execute(c));
//    }

    /* covered by others */
//    @Test
//    void insertPresetCategoriesIntoDB() {
//    }
//
//    @Test
//    void fieldsForTableCreation() {
//    }
//
//    @Test
//    void getCid() {
//    }
//
//    @Test
//    void getName() {
//    }
//
//    @Test
//    void getCategories() {
//    }

    @Test
    void getPresetCategories() {
        List<Category> categoryList = Category.getPresetCategories();
        Assertions.assertNotNull(categoryList);
        Assertions.assertEquals("Groceries", categoryList.get(0).getName());
        Assertions.assertEquals(1, categoryList.get(0).getCid());
    }
}
