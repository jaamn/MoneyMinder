package UnitTest.Models;

import Models.Category;
import Models.CategoryPricePair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryPricePairTest {
    CategoryPricePair cpp;

    @BeforeEach
    void setUp() {
        Category c = new Category(7, "Gifts");
        cpp = new CategoryPricePair(c, "Necklace", (float) 3.75);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPrice() {
        Assertions.assertEquals(3.75, cpp.getPrice());
    }

    @Test
    void getCategoryName() {
        Assertions.assertEquals("Gifts", cpp.getCategoryName().getValue());
    }

    @Test
    void getPriceProp() {
        Assertions.assertEquals("3.75", cpp.getPriceProp().getValue());
    }

    @Test
    void getItemProp() {
        Assertions.assertEquals("Necklace", cpp.getItemProp().getValue());
    }
}