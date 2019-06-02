package Controllers;

import Models.User;
import Utils.SwitchScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.IOException;

public class AnalyticsFilterController {

    @FXML
    private Button pieChartButton;

    @FXML
    private Button lineGraphButton;

    @FXML
    private Button metricsButton;

    private User user;

    public AnalyticsFilterController(User user)
    {
        this.user = user;
    }

    @FXML
    public void initialize() throws IOException {

        pieChartButton.setOnAction( event -> {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            switchTopieChart();
        });

        lineGraphButton.setOnAction( event -> {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            switchTolineGraph();
        });

        metricsButton.setOnAction( event -> {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            switchToMetrics();
        });
    }

    private void switchTopieChart(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("FXML/PieChartPage.fxml"));
        PieChartController controller = new PieChartController(this.user);
        loader.setController(controller);
        SwitchScene.switchScene(loader, "Pie Chart");
    }

    private void switchTolineGraph(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("FXML/LineChartPage.fxml"));
        LineChartController controller = new LineChartController(this.user);
        loader.setController(controller);
        SwitchScene.switchScene(loader, "Line Chart");
    }

    private void switchToMetrics(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("FXML/MetricsPage.fxml"));
        MetricsController controller = new MetricsController(this.user);
        loader.setController(controller);
        SwitchScene.switchScene(loader, "Metrics");
    }
}
