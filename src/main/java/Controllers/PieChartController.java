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

public class PieChartController extends BackToAnalyticsFilter{

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
            switchBackToAnalyticsFilter(user);
        });

//        filterButton.setOnAction(event -> {
//            filterEvent(piechartdata);
//        });
    }

//    private void filterEvent(ObservableList piechartdata){
//        FilteredList filteredItems = new FilteredList(piechartdata);
//        filteredItems.predicateProperty().bind(Bindings.createObjectBinding(() -> {
//                    LocalDate minDate = startDate.getValue();
//                    LocalDate maxDate = endDate.getValue();
//
//                    // get final values != null
//                    final LocalDate finalMin = minDate == null ? LocalDate.MIN : minDate;
//                    final LocalDate finalMax = maxDate == null ? LocalDate.MAX : maxDate;
//
//                    // values for openDate need to be in the interval [finalMin, finalMax]
//                    return ti -> !finalMin.isAfter(piechartdata.getOpenDate()) && !finalMax.isBefore(ti.getOpenDate());
//                },
//                startDate.valueProperty(),
//                endDate.valueProperty()));
//    }

    private void loadData(){

        // TODO
        // Item.getSumPriceForCategory(user, c);
        // will return a sum of the prices of all items in the Category c for the User user

        // Categories: Category.getCategories();
        // will return a list of all categories in the database

        // Items: Item.getItemsForUser(user);
        // will return a list of all items for a User user

        piechartdata = FXCollections.observableArrayList();
        //EXAMPLE:
        for (Category c : Category.getCategories())
        {
            float total = Item.getSumPriceForCategory(user, c);
            piechartdata.add(new PieChart.Data(c.toString(), total));
            System.err.println(c.getName() + ": " + total);
        }
    }
}
