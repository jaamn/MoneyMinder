package UnitTest.Models;

import Models.DateContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateContainerTest {

    @Test
    void testMonth() {
        Assertions.assertEquals("06", DateContainer.Date.month());
    }

    @Test
    void testMonth2() {
        Assertions.assertEquals("01", DateContainer.Date.month("January"));
        Assertions.assertEquals("01", DateContainer.Date.month(("January").toLowerCase()));
        Assertions.assertEquals("01", DateContainer.Date.month(("January").toUpperCase()));
        Assertions.assertEquals("02", DateContainer.Date.month("February"));
        Assertions.assertEquals("02", DateContainer.Date.month(("February").toLowerCase()));
        Assertions.assertEquals("02", DateContainer.Date.month(("February").toUpperCase()));
        Assertions.assertEquals("03", DateContainer.Date.month("March"));
        Assertions.assertEquals("03", DateContainer.Date.month(("March").toLowerCase()));
        Assertions.assertEquals("03", DateContainer.Date.month(("March").toUpperCase()));
        Assertions.assertEquals("04", DateContainer.Date.month("April"));
        Assertions.assertEquals("04", DateContainer.Date.month(("April").toLowerCase()));
        Assertions.assertEquals("04", DateContainer.Date.month(("April").toUpperCase()));
        Assertions.assertEquals("05", DateContainer.Date.month("May"));
        Assertions.assertEquals("05", DateContainer.Date.month(("May").toLowerCase()));
        Assertions.assertEquals("05", DateContainer.Date.month(("May").toUpperCase()));
        Assertions.assertEquals("06", DateContainer.Date.month("June"));
        Assertions.assertEquals("06", DateContainer.Date.month(("June").toLowerCase()));
        Assertions.assertEquals("06", DateContainer.Date.month(("June").toUpperCase()));
        Assertions.assertEquals("07", DateContainer.Date.month("July"));
        Assertions.assertEquals("07", DateContainer.Date.month(("July").toLowerCase()));
        Assertions.assertEquals("07", DateContainer.Date.month(("July").toUpperCase()));
        Assertions.assertEquals("08", DateContainer.Date.month("August"));
        Assertions.assertEquals("08", DateContainer.Date.month(("August").toLowerCase()));
        Assertions.assertEquals("08", DateContainer.Date.month(("August").toUpperCase()));
        Assertions.assertEquals("09", DateContainer.Date.month("September"));
        Assertions.assertEquals("09", DateContainer.Date.month(("September").toLowerCase()));
        Assertions.assertEquals("09", DateContainer.Date.month(("September").toUpperCase()));
        Assertions.assertEquals("10", DateContainer.Date.month("October"));
        Assertions.assertEquals("10", DateContainer.Date.month(("October").toLowerCase()));
        Assertions.assertEquals("10", DateContainer.Date.month(("October").toUpperCase()));
        Assertions.assertEquals("11", DateContainer.Date.month("November"));
        Assertions.assertEquals("11", DateContainer.Date.month(("November").toLowerCase()));
        Assertions.assertEquals("11", DateContainer.Date.month(("November").toUpperCase()));
        Assertions.assertEquals("12", DateContainer.Date.month("December"));
        Assertions.assertEquals("12", DateContainer.Date.month(("December").toLowerCase()));
        Assertions.assertEquals("12", DateContainer.Date.month(("December").toUpperCase()));
        Assertions.assertEquals("00", DateContainer.Date.month("Lol"));

    }

    @Test
    void testYear() {
        Assertions.assertEquals("2019", DateContainer.Date.year());
    }

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
    }



}