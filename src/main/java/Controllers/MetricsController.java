package Controllers;

import Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import java.time.YearMonth;
import java.time.ZoneId;

public class MetricsController extends Returnable{

    @FXML
    private Text totalSpending;

    @FXML
    private TableColumn<CategoryPricePair, String> MostCategoryName;

    @FXML
    private TableView MostSpending;

    @FXML
    private TableView LeastSpending;

    @FXML
    private TableColumn<CategoryPricePair, String> MostPrice;

    @FXML
    private TableColumn<CategoryPricePair, String> MostItem;

    @FXML
    private TableColumn<CategoryPricePair, String> LeastCategoryName;

    @FXML
    private TableColumn<CategoryPricePair, String> LeastPrice;

    @FXML
    private TableColumn<CategoryPricePair, String> LeastItem;

    @FXML
    private Button returnButton;

    private User user;

    private ObservableList<CategoryPricePair> msList = FXCollections.observableArrayList();

    private ObservableList<CategoryPricePair> lsList = FXCollections.observableArrayList();

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
        float totalMoney = 0;
        for(Category c: Category.getCategories()){
            totalMoney += Receipt.getSpendingPerCategoryForMonth(user, c, monthStr, yrStr);
            CategoryPricePair min = Item.getMinPriceForCategoryForMonth(user, c, monthStr, yrStr);
            if (min != null)
            {
                lsList.add(min);
            }
            CategoryPricePair max = Item.getMaxPriceForCategoryForMonth(user, c, monthStr, yrStr);
            if (max != null)
            {
                msList.add(max);
            }
        }
        totalSpending.setText("$" + totalMoney);

        LeastCategoryName.setCellValueFactory(cellData -> cellData.getValue().getCategoryName());
        MostCategoryName.setCellValueFactory(cellData -> cellData.getValue().getCategoryName());
        LeastPrice.setCellValueFactory(cellData -> cellData.getValue().getPriceProp());
        MostPrice.setCellValueFactory(cellData -> cellData.getValue().getPriceProp());
        LeastItem.setCellValueFactory(cellData -> cellData.getValue().getItemProp());
        MostItem.setCellValueFactory(cellData -> cellData.getValue().getItemProp());

        MostSpending.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        MostSpending.setItems(msList);

        LeastSpending.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        LeastSpending.setItems(lsList);


        /*
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
        */

        returnButton.setOnAction(event -> {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            returnToPage(user, "FXML/AnalyticsFilter.fxml", "Analytics filter", "analytics");
        });
    }
}
