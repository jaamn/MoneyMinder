package Controllers;

import Models.Item;
import Models.User;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HistoryController {

    @FXML
    TableView<Item> historyTableView;

    @FXML
    TableColumn<Item, String> dateColumn;

    @FXML
    TableColumn<Item, String> storeColumn;

    @FXML
    TableColumn<Item, String> amountColumn;

    @FXML
    TableColumn<Item, String> categoryColumn;

    private User user;

    public HistoryController(User user)
    {
        this.user = user;
    }

}
