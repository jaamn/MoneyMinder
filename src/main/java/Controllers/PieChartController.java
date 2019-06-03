package Controllers;

import Models.Category;
import Models.Item;
import Models.User;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;

public class PieChartController {

    @FXML
    private PieChart pieChart;

    @FXML
    private Button filterButton;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    private ObservableList piechartdata;

    private User user;

    public PieChartController(User user)
    {
        this.user = user;
    }

    @FXML
    public void initialize(){
//        loadData();
        loadDemoData();
        pieChart.setData(piechartdata);

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
        // These below will return ObservableArrayLists of the Categories and of the user's items
        // Categories: Category.getCategories();
        // Items: Item.getItemsForUser(user);

        Connection connection;
        piechartdata = FXCollections.observableArrayList();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            JOptionPane.showMessageDialog(null, "Connected");
            PreparedStatement ps = connection.prepareStatement("select * FROM Categories");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                //name and value
                piechartdata.add(new PieChart.Data(rs.getString("name"), rs.getInt(1)));
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void loadDemoData(){

        piechartdata = FXCollections.observableArrayList(
                new PieChart.Data("Groceries", 13),
                new PieChart.Data("Personal", 25),
                new PieChart.Data("Entertainment", 10),
                new PieChart.Data("Transportation", 22),
                new PieChart.Data("Bills", 30),
                new PieChart.Data("One-offs", 50));
    }
}
