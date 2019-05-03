package Controllers;

import Utils.SwitchScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField firstnameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    public void initialize(){

        registerButton.setOnAction( event -> {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            switchToLoginScene();
        });

    }

    private void switchToLoginScene() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("FXML/Login.fxml"));
        SwitchScene.switchScene(loader, "Login");
    }

}
