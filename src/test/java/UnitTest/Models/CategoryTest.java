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

    @Test
    void getPresetCategories() {
        /*  The following Unit Test tests getPresetCategories(),
            - a function in the class Category
            - preps default fields for database
         */
        List<Category> categoryList = Category.getPresetCategories();
        Assertions.assertNotNull(categoryList);
        Assertions.assertEquals("Groceries", categoryList.get(0).getName());
        Assertions.assertEquals(1, categoryList.get(0).getCid());
        Assertions.assertEquals("Personal", categoryList.get(1).getName());
        Assertions.assertEquals(2, categoryList.get(1).getCid());
        Assertions.assertEquals("Entertainment", categoryList.get(2).getName());
        Assertions.assertEquals(3, categoryList.get(2).getCid());
        Assertions.assertEquals("Transportation", categoryList.get(3).getName());
        Assertions.assertEquals(4, categoryList.get(3).getCid());
        Assertions.assertEquals("Bills", categoryList.get(4).getName());
        Assertions.assertEquals(5, categoryList.get(4).getCid());
        Assertions.assertEquals("One-offs", categoryList.get(5).getName());
        Assertions.assertEquals(6, categoryList.get(5).getCid());
    }
}
