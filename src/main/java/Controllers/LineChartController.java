package Controllers;

import Models.Category;
import Models.Receipt;
import Models.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.text.DateFormatSymbols;
import java.util.Calendar;

public class LineChartController {

    private User user;

    @FXML
    private LineChart lineChart;

    @FXML
    private CategoryAxis months;

    @FXML
    private NumberAxis totalSpending;

    private XYChart.Series<String, Integer> series;

    public LineChartController(User user)
    {
        this.user = user;
    }

    public void initialize(){
        loadChart();
    }

    private String monthToSQL(String month){
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

    private void loadChart(){
        DateFormatSymbols dfs = new DateFormatSymbols(); // encapsulate date-time formatting data
        String[] months = dfs.getMonths(); // extract the months
        String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)); // extract the current year
        // Should be in FOR LOOP by category
        ObservableList<Category> categories = Category.getCategories();
        for(Category category : categories){
            XYChart.Series series = new XYChart.Series(); // should be series PER CATEGORY
            series.setName(category.getName()); // name series that category
            double categorySum = 0;
            for(String month : months) {
                double monthSum = Receipt.getSpendingPerCategoryForMonth(
                        user,
                        category,
                        monthToSQL(month),
                        year);
                categorySum += monthSum;
                series.getData().add(new XYChart.Data(month, monthSum));
            }
            if(0 != categorySum) lineChart.getData().add(series);
        }
    }
}
