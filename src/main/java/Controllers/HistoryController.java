package Controllers;

import Models.Category;
import Models.Item;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class HistoryController {

    @FXML
    TableView<Item> itemTable;

    @FXML
    TableColumn<Item, String> itemName;

    @FXML
    TableColumn<Item, String> itemQuantity;

    @FXML
    TableColumn<Item, String> itemCategory;

    @FXML
    TableColumn<Item, String> itemRid;

    @FXML
    TableColumn<Item, String> itemPrice;

    private User user;

    private ObservableList<Item> items = FXCollections.observableArrayList();


    public HistoryController(User user)
    {
        this.user = user;
    }

    @FXML
    public void initialize() throws IOException {

        itemCategory.setCellValueFactory(cellData -> cellData.getValue().getCategoryName());
        itemQuantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
        itemName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        itemPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        itemRid.setCellValueFactory(cellData -> cellData.getValue().ridProperty());

        itemTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        items = Item.getItemsForUser(user);
        itemTable.setItems(items);
    }

}
