package Controllers;

import Models.User;
import Utils.SwitchScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Button enterReceiptButton;

    @FXML
    private Button viewAnalyticsButton;

    @FXML
    private Button viewHistoryButton;

    private User user;

    public MainMenuController(User user)
    {
        this.user = user;
    }

    @FXML
    public void initialize() throws IOException {

        welcomeLabel.setText("Welcome back, " + user.getFirstName());

        enterReceiptButton.setOnAction( event -> {
//            ((Node)(event.getSource())).getScene().getWindow().hide();
            switchToEnterReceiptScreen();
        });

        viewAnalyticsButton.setOnAction( event -> {
//            ((Node)(event.getSource())).getScene().getWindow().hide();
            switchToViewAnalyticsScreen();
        });

        viewHistoryButton.setOnAction( event -> {
//            ((Node)(event.getSource())).getScene().getWindow().hide();
            switchToHistoryScreen();
        });

    }

    private void switchToEnterReceiptScreen() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("FXML/EnterNewReceipt.fxml"));
        EnterReceiptController controller = new EnterReceiptController(this.user);
        loader.setController(controller);
        SwitchScene.switchScene(loader, "Enter Receipt");
    }

    private void switchToViewAnalyticsScreen() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("FXML/AnalyticsFilter.fxml"));
        AnalyticsFilterController controller = new AnalyticsFilterController(this.user);
        loader.setController(controller);
        SwitchScene.switchScene(loader, "View Analytics");
    }

    private void switchToHistoryScreen() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("FXML/History.fxml"));
        HistoryController controller = new HistoryController(this.user);
        loader.setController(controller);
        SwitchScene.switchScene(loader, "View History");
    }
}
