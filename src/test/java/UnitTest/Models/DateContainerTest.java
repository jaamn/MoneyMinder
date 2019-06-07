package UnitTest.Models;

import Models.DateContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateContainerTest {

    @Test
    void testMonth() {
        Assertions.assertEquals("06", DateContainer.Date.month());

    };

    @Test
    void testYear() {
        Assertions.assertEquals("2019", DateContainer.Date.year());
    };

    @Test
    void testCalendar() {
        String[] months = DateContainer.Date.months();
        int i = 0;
        Assertions.assertEquals("January", months[i++]);
        Assertions.assertEquals("February", months[i++]);
        Assertions.assertEquals("March", months[i++]);
        Assertions.assertEquals("April", months[i++]);
        Assertions.assertEquals("May", months[i++]);
        Assertions.assertEquals("June", months[i++]);
        Assertions.assertEquals("July", months[i++]);
        Assertions.assertEquals("August", months[i++]);
        Assertions.assertEquals("September", months[i++]);
        Assertions.assertEquals("October", months[i++]);
        Assertions.assertEquals("November", months[i++]);
        Assertions.assertEquals("December", months[i++]);
    };



}