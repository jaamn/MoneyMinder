package Controllers;

import Models.Category;
import Models.Item;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

public class PieChartController extends Returnable{

    @FXML
    private PieChart pieChart;

    @FXML
    private Button filterButton;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private Button returnButton;

    private ObservableList piechartdata;

    private User user;

    public PieChartController(User user)
    {
        this.user = user;
    }

    @FXML
    public void initialize(){
        loadData();
        pieChart.setData(piechartdata);
        returnButton.setOnAction(event -> {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            returnToPage(user, "FXML/AnalyticsFilter.fxml", "Analytics Filter", "analytics");
        });
    }

    private void loadData(){

        piechartdata = FXCollections.observableArrayList();
        for (Category c : Category.getCategories())
        {
            float total = Item.getSumPriceForCategory(user, c);
            piechartdata.add(new PieChart.Data(c.toString(), total));
            System.err.println(c.getName() + ": " + total);
        }
    }
}
