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
            switch (month.toLowerCase()) {
                case "january":
                    return "01";
                case "february":
                    return "02";
                case "march":
                    return "03";
                case "april":
                    return "04";
                case "may":
                    return "05";
                case "june":
                    return "06";
                case "july":
                    return "07";
                case "august":
                    return "08";
                case "september":
                    return "09";
                case "october":
                    return "10";
                case "november":
                    return "11";
                case "december":
                    return "12";
                default:
                    return "00";
            }
        }
    }
}
