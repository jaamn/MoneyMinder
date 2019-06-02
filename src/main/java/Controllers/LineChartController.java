package Controllers;

import Models.User;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        loadDemoChart();
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
        // I added price field in item model;
        // we need another model to organize our spending by month
        // select month, amount FROM my_spending ORDER BY month asc
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


    }

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
}
