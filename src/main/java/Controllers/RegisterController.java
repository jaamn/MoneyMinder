package Controllers;

import Models.User;
import Utils.SwitchScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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
            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty() || firstnameField.getText().isEmpty() || lastnameField.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields\n");
                alert.showAndWait();
            }
            else {
                User user = new User(usernameField.getText(), firstnameField.getText(), lastnameField.getText(), passwordField.getText());
                if (user.registerUser())
                {
                    // what if we want to go back to login page?
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    switchToLoginScene();
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Error\n");
                    alert.showAndWait();
                }
            }
        });

    }

    private void switchToLoginScene() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("FXML/Login.fxml"));
        SwitchScene.switchScene(loader, "Login");
    }

}
