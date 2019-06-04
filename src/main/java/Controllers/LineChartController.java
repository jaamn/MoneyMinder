package Controllers;

import Models.Category;
import Models.Receipt;
import Models.Tables;
import Models.User;
import Utils.SQL.QueryFactory.InsertQueryFactory;
import Utils.SQL.QueryStatements.InsertQueries.InsertQuery;
import com.sun.org.apache.bcel.internal.generic.SWITCH;
import javafx.collections.ObservableList;
import javafx.embed.swt.SWTFXUtils;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import sun.util.resources.CalendarData;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.time.Month;
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
        //loadDemoChart();
        loadChart();
    }

    private void loadDemoChart(){
        XYChart.Series series = new XYChart.Series();
        series.setName("2019 DEMO");
        series.getData().add(new XYChart.Data("Jan", 23));
        series.getData().add(new XYChart.Data("Feb", 14));
        series.getData().add(new XYChart.Data("Mar", 15));
        series.getData().add(new XYChart.Data("Apr", 24));
        series.getData().add(new XYChart.Data("May", 34));
        series.getData().add(new XYChart.Data("Jun", 36));
        series.getData().add(new XYChart.Data("Jul", 22));
        series.getData().add(new XYChart.Data("Aug", 45));
        series.getData().add(new XYChart.Data("Sep", 43));
        series.getData().add(new XYChart.Data("Oct", 17));
        series.getData().add(new XYChart.Data("Nov", 29));
        series.getData().add(new XYChart.Data("Dec", 25));
        lineChart.getData().add(series);

    }

    private void loadChart(){
        // TODO

        DateFormatSymbols dfs = new DateFormatSymbols(); // encapsulate date-time formatting data
        String[] months = dfs.getMonths(); // extract the months
        String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)); // extract the current year
        DecimalFormat df = new DecimalFormat("00"); // month formatter

        // Should be in FOR LOOP by category
        ObservableList<Category> categories = Category.getCategories();
        for(Category category : categories){
            XYChart.Series series = new XYChart.Series(); // should be series PER CATEGORY
            series.setName(category.getName()); // name series that category
            for(String month : months) {
                series.getData().add(new XYChart.Data(month, Receipt.getSpendingPerCategoryForMonth(
                        user,
                        category,
                        df.format(Month.valueOf(month.toUpperCase()).getValue()), // ERROR
                        year)));
                // will return the sum of the price of all items for that user that month
                // EXAMPLE
                System.err.println(Receipt.getSpendingForMonth(user, month, year));
            }
            lineChart.getData().add(series);
        }

        // I added price field in item model;
        // we need another model to organize our spending by month
        // select month, amount FROM my_spending ORDER BY month asc

        /*
        // USE SQL FACTORIES OR STATIC METHODS IN MODEL CLASSES instead of this

        String query = "select month, amount FROM my_spending ORDER BY month asc";
        series = new XYChart.Series<>();
        try {
            // connect to db
            Connection connection = connectDB();

            // execute query and store in resultset
            ResultSet rs = connection.createStatement().executeQuery(query);
            while(rs.next()){
//                series.getData().add(new XYChart.Data<>(rs.getString(), rs.getInt()));
            }
            lineChart.getData().add(series);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        */
    }

    /*
    USE SQL FACTORIES OR STATIC METHODS IN MODEL CLASSES instead of this
    private Connection connectDB(){
        try{
            Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            JOptionPane.showMessageDialog(null, "Connected");
            return connection;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }
    */
}
