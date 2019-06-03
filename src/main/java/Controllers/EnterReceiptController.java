package Controllers;

import Models.*;
import Utils.SQL.QueryFactory.InsertQueryFactory;
import Utils.SQL.QueryStatements.InsertQueries.InsertQuery;
import Utils.SQL.QueryStatements.SelectQueries.SelectQuery;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

import static Models.Tables.Stores;

public class EnterReceiptController {

    private User user;

    @FXML
    TableView itemTable;

    @FXML
    TableColumn<Item, String> itemCategory;

    @FXML
    TableColumn<Item, String> itemQuantity;

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

    private ObservableList<Item> items = FXCollections.observableArrayList();

    public EnterReceiptController(User user)
    {
        this.user = user;
    }

    @FXML
    public void initialize() throws IOException {

        itemCategory.setCellValueFactory(cellData -> cellData.getValue().getCategoryName());
        itemQuantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
        itemTable.setItems(items);

        submitButton.setOnAction(event -> {
            handleSubmit();
        });

        addItemButton.setOnAction(event -> {
            showAddItemDialog();
        });

    }

    private void handleSubmit() {
        if (isInputValid()) {
            // TODO store field?
            int rid = Integer.parseInt(receiptIDField.getText());
            int sid = Store.getIdFromName(storeField.getText());
            java.sql.Date date = Date.valueOf(dateField.getValue());
            Receipt r = new Receipt(rid, sid, this.user.getUsername(), date);

            InsertQuery insert = InsertQueryFactory.getQuery(Tables.Receipts);
            insert.execute(r);

            for (Item i : items)
            {
                i.setRid(rid);
                InsertQuery insertItem = InsertQueryFactory.getQuery(Tables.Items);
                insertItem.execute(i);
            }
        }
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
            //alert.initOwner(dialogStage);
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
