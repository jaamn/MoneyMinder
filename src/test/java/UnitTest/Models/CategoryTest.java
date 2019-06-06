package UnitTest.Models;
import Models.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class CategoryTest {

    @Test
    void TestgetPresetCategories(){
        List<Category> categoryList = Category.getPresetCategories();
        Assertions.assertNotNull(categoryList);
        Assertions.assertEquals("Groceries", categoryList.get(0).getName());
        Assertions.assertEquals(1, categoryList.get(0).getCid());
    }
}
