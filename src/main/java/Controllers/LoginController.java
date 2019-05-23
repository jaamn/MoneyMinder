package Controllers;

import Models.Category;
import Models.User;
import Utils.SwitchScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static Models.User.verifyUser;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    public void initialize(){

        loginButton.setOnAction( event -> {
            User user = verifyUser(usernameField.getText(), passwordField.getText());
            if (user != null)
            {
                switchToMainMenu();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error\n");
                alert.showAndWait();
            }
        });

        registerButton.setOnAction( event -> {
//            ((Node)(event.getSource())).getScene().getWindow().hide();
            switchToRegisterScene();
        });

    }

    private void switchToRegisterScene() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("FXML/Register.fxml"));
        SwitchScene.switchScene(loader, "Register");
    }

    private void switchToMainMenu() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("FXML/MainMenu.fxml"));
        SwitchScene.switchScene(loader, "Main Menu");
    }

}
