package Models;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public final class DateContainer {
    public static class Date {
        static DateFormatSymbols dfs = new DateFormatSymbols(); // encapsulate date-time formatting data
        static String[] months = dfs.getMonths(); // extract the months
        static String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)); // extract the current year
        static String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH)); // extract the current month

        public static String[] months(){
            return months;
        }
        public static String year() {
            return year;
        }
        public static String month() { return month(month); }
        public static String month(String month){
            switch (month) {
                case "January":
                    return "01";
                case "February":
                    return "02";
                case "March":
                    return "03";
                case "April":
                    return "04";
                case "May":
                    return "05";
                case "June":
                    return "06";
                case "July":
                    return "07";
                case "August":
                    return "08";
                case "September":
                    return "09";
                case "October":
                    return "10";
                case "November":
                    return "11";
                case "December":
                    return "12";
                default:
                    return "00";
            }
        }
    }
}
