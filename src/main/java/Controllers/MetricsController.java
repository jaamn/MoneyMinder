package Controllers;

import Models.*;
import Models.DateContainer.Date;
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

        // filter by month
        float totalMoney = 0;
        for(Category c: Category.getCategories()){
            totalMoney += Receipt.getSpendingPerCategoryForMonth(user, c, Date.month(), Date.year());
            CategoryPricePair min = Item.getMinPriceForCategoryForMonth(user, c, Date.month(), Date.year());
            if (min.getPrice() != 0)
            {
                lsList.add(min);
            }
            CategoryPricePair max = Item.getMaxPriceForCategoryForMonth(user, c, Date.month(), Date.year());
            if (max.getPrice() != 0)
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

        returnButton.setOnAction(event -> {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            returnToPage(user, "FXML/AnalyticsFilter.fxml", "Analytics filter", "analytics");
        });
    }
}
