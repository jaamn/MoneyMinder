package Controllers;

import Models.*;
import Utils.SwitchScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

public class EnterReceiptController extends Returnable{

    private User user;

    @FXML
    TableView itemTable;

    @FXML
    TableColumn<Item, String> itemCategory;

    @FXML
    TableColumn<Item, String> itemQuantity;

    @FXML
    TableColumn<Item, String> itemName;

    @FXML
    TableColumn<Item, String> itemPrice;

    @FXML
    TextField receiptIDField;

    @FXML
    DatePicker dateField;

    @FXML
    TextField storeField;

    @FXML
    Button submitButton;

    @FXML
    Button addItemButton;

    @FXML
    Button returnButton;

    private ObservableList<Item> items = FXCollections.observableArrayList();

    public EnterReceiptController(User user)
    {
        this.user = user;
    }

    @FXML
    public void initialize() throws IOException {

        items.clear();;
        itemTable.setPlaceholder(new Label("Enter Items"));
        itemCategory.setCellValueFactory(cellData -> cellData.getValue().getCategoryName());
        itemQuantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
        itemName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        itemPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        itemTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        itemTable.setItems(items);

        submitButton.setOnAction(event -> {
            handleSubmit();
        });

        addItemButton.setOnAction(event -> {
            showAddItemDialog();
        });

        returnButton.setOnAction(event -> {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            returnToPage(user, "FXML/MainMenu.fxml", "Main Menu", "main");
        });
    }

    private void handleSubmit() {
        if (isInputValid()) {
            int rid = Integer.parseInt(receiptIDField.getText());
            int sid = Store.getIdFromName(storeField.getText());
            java.sql.Date date = Date.valueOf(dateField.getValue());
            Receipt r = new Receipt(rid, sid, this.user.getUsername(), date);

            r.insertIntoDB();

            for (Item i : items)
            {
                i.setRid(rid);
                i.insertIntoDB();
            }
            switchToMainMenu(user);
        }
    }

    private void switchToMainMenu(User user) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("FXML/MainMenu.fxml"));
        MainMenuController controller = new MainMenuController(user);
        loader.setController(controller);
        SwitchScene.switchScene(loader, "Main Menu");
    }

    private boolean isInputValid()
    {
        // TODO test this and add one for date

        String errorMessage = "";

        if (receiptIDField.getText() == null)
        {
            errorMessage += "Enter Receipt ID\n";
        }

        if (storeField.getText() == null)
        {
            errorMessage += "Enter Store\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    private void showAddItemDialog()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("FXML/AddItemDialog.fxml"));
        Item item = new Item();
        AddItemDialogController controller = new AddItemDialogController(item);
        loader.setController(controller);

        Stage stage = new Stage();
        controller.setDialogStage(stage);
        stage.setTitle("Add Item");
        Pane layout = null;
        try {
            layout = (Pane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene landingScene = new Scene(layout);
        stage.setScene(landingScene);
        stage.setResizable(false);
        stage.showAndWait();

        if (controller.isOkClicked()) {
            items.add(item);
            itemTable.setItems(items);
        }

    }
}
