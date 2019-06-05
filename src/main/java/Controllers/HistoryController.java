package Controllers;

import Models.Item;
import Models.ItemReceiptPair;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class HistoryController extends Returnable{

    @FXML
    TableView<ItemReceiptPair> itemTable;

    @FXML
    TableColumn<ItemReceiptPair, String> itemName;

    @FXML
    TableColumn<ItemReceiptPair, String> itemQuantity;

    @FXML
    TableColumn<ItemReceiptPair, String> itemCategory;

    @FXML
    TableColumn<ItemReceiptPair, String> itemRid;

    @FXML
    TableColumn<ItemReceiptPair, String> itemPrice;

    @FXML
    TableColumn<ItemReceiptPair, String> storeCol;

    @FXML
    TableColumn<ItemReceiptPair, String> dateCol;

    @FXML
    Button returnButton;

    private User user;

    private ObservableList<ItemReceiptPair> items = FXCollections.observableArrayList();


    public HistoryController(User user)
    {
        this.user = user;
    }

    @FXML
    public void initialize() throws IOException {

        items.clear();
        itemCategory.setCellValueFactory(cellData -> cellData.getValue().getItem().getCategoryName());
        itemQuantity.setCellValueFactory(cellData -> cellData.getValue().getItem().quantityProperty());
        itemName.setCellValueFactory(cellData -> cellData.getValue().getItem().nameProperty());
        itemPrice.setCellValueFactory(cellData -> cellData.getValue().getItem().priceProperty());
        itemRid.setCellValueFactory(cellData -> cellData.getValue().getItem().ridProperty());
        storeCol.setCellValueFactory(cellData -> cellData.getValue().getReceipt().getStoreName());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().getReceipt().getDateProp());

        itemTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        items = Item.getItemsForUser(user);
        itemTable.setItems(items);

        returnButton.setOnAction(event -> {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            returnToPage(user, "FXML/MainMenu.fxml", "Main Menu", "main");
        });
    }
}
