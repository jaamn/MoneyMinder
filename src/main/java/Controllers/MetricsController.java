package Controllers;

import Models.Category;
import Models.Item;
import Models.Receipt;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.time.YearMonth;
import java.time.ZoneId;
import java.util.List;

public class MetricsController {

    @FXML
    private Text totalSpending;

    @FXML
    private TableColumn<Item, String> MostCategoryName;

    @FXML
    private TableView MostSpending;

    @FXML
    private TableView LeastSpending;

    @FXML
    private TableColumn<Item, String> MostPrice;

    @FXML
    private TableColumn<Item, String> LeastCategoryName;

    @FXML
    private TableColumn<Item, String> LeastPrice;

    private User user;

    private ObservableList<Item> msList = FXCollections.observableArrayList();

    private ObservableList<Item> lsList = FXCollections.observableArrayList();

    public MetricsController(User user)
    {
        this.user = user;
    }

    public void initialize(){
        ZoneId z = ZoneId.of("America/Los_Angeles");
        YearMonth ym = YearMonth.now(z);
        int year = ym.getYear();
        int month = ym.getMonthValue();
        String yrStr = Integer.toString(year);
        String monthStr = Integer.toString(month);
        if(monthStr.length() == 1){
            monthStr = "0".concat(monthStr);
        }
        // filter by month
        int totalMoney = 0;
        for(Category c: Category.getCategories()){
            totalMoney += (int)Receipt.getSpendingPerCategoryForMonth(user, c, monthStr, yrStr);
        }
        totalSpending.setText("$" + totalMoney);

        for(Category c: Category.getCategories()){
            MostPrice.setCellValueFactory(cellData -> cellData.getValue().getMaxProperty(user, c));
        }
        MostCategoryName.setCellValueFactory(cellData -> cellData.getValue().getCategoryName());
        MostSpending.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        msList = Item.getItemsForUser(user);
        MostSpending.setItems(msList);

        for(Category c: Category.getCategories()){
            LeastPrice.setCellValueFactory(cellData -> cellData.getValue().getMinProperty(user, c));
        }
        LeastCategoryName.setCellValueFactory(cellData -> cellData.getValue().getCategoryName());
        lsList = Item.getItemsForUser(user);
        LeastSpending.setItems(lsList);
    }
}
