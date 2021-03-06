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

    User user = null;

    @FXML
    public void initialize(){

        loginButton.setOnAction( event -> {
            user = verifyUser(usernameField.getText(), passwordField.getText());
            if (user != null)
            {
                switchToMainMenu(user);
                ((Node) (event.getSource())).getScene().getWindow().hide(); /* better navigation */
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Please fill in all fields.\n" +
                                "Make sure your username and password are correct.");
                alert.showAndWait();
            }
        });

        registerButton.setOnAction( event -> {
            switchToRegisterScene();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        });
    }

    private void switchToRegisterScene() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("FXML/Register.fxml"));
        SwitchScene.switchScene(loader,"Register");
    }

    private void switchToMainMenu(User user) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("FXML/MainMenu.fxml"));
        MainMenuController controller = new MainMenuController(user);
        loader.setController(controller);
        SwitchScene.switchScene(loader, "Main Menu");
    }

}
