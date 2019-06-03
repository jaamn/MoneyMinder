package Controllers;

import Models.Category;
import Models.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddItemDialogController {

    @FXML
    Button submitButton;

    @FXML
    Button cancelButton;

    @FXML
    TextField quantity;

    @FXML
    TextField price;

    @FXML
    TextField name;

    @FXML
    ComboBox<Category> category;

    private Stage dialogStage;
    private Item item;
    private boolean okClicked = false;

    private ObservableList<Category> categories = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        cancelButton.setOnAction(event -> {
            dialogStage.close();
        });

        submitButton.setOnAction(event -> {
            handleSubmit();
        });

        category.setItems(categories);
    }

    public AddItemDialogController(Item item) {
        this.item = item;
        categories = Category.getCategories();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    private void handleSubmit() {
        if (isInputValid()) {
            item.setCid(category.getValue().getCid());
            item.setQuantity(Integer.parseInt(quantity.getText()));
            item.setPrice(Float.parseFloat(price.getText()));
            item.setName(name.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    private boolean isInputValid()
    {
        // TODO test this

        String errorMessage = "";

        if (category.getValue() == null)
        {
            errorMessage += "Select category\n";
        }

        if (quantity.getText() == null)
        {
            errorMessage += "Enter quantity\n";
        }
        if (name.getText() == null)
        {
            errorMessage += "Enter name\n";
        }
        else
        {
            try {
                Integer.parseInt(quantity.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Quantity must be integer\n";
            }
        }

        if (price.getText() == null)
        {
            errorMessage += "Enter price\n";
        }
        else
        {
            try {
                Float.parseFloat(price.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Price must be number\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}
