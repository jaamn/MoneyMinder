package Controllers;

import Models.Category;
import Models.Receipt;
import Models.User;
import Models.DateContainer.Date;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

public class LineChartController extends Returnable{

    private User user;

    @FXML
    private LineChart lineChart;

    @FXML
    private CategoryAxis months;

    @FXML
    private NumberAxis totalSpending;

    @FXML
    private Button returnButton;

    private XYChart.Series<String, Integer> series;

    public LineChartController(User user)
    {
        this.user = user;
    }

    public void initialize(){
        loadChart();
        returnButton.setOnAction(event -> {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            returnToPage(user, "FXML/AnalyticsFilter.fxml", "Analytics Filter", "analytics");
        });
    }

    private void loadChart(){
        ObservableList<Category> categories = Category.getCategories();
        for(Category category : categories){
            XYChart.Series series = new XYChart.Series(); // should be series PER CATEGORY
            series.setName(category.getName()); // name series that category
            double categorySum = 0;
            for(String month : Date.months()) {
                double monthSum = Receipt.getSpendingPerCategoryForMonth(
                        user,
                        category,
                        Date.month(month),
                        Date.year());
                categorySum += monthSum;
                series.getData().add(new XYChart.Data(month, monthSum));
            }
            if(0 != categorySum) lineChart.getData().add(series);
        }
    }
}
