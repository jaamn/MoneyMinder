
import Models.Category;
import Models.Tables;
import Utils.SQL.QueryFactory.CreateTableQueryFactory;
import Utils.SQL.QueryFactory.InsertQueryFactory;
import Utils.SQL.QueryStatements.CreateTableQueries.*;
import Utils.SQL.QueryStatements.InsertQueries.InsertQuery;
import Utils.SwitchScene;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.util.EnumSet;

public class MainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) {

        createTables();

        Platform.setImplicitExit(false);
        this.primaryStage = stage;
        this.primaryStage.setTitle("MoneyMinder");
        this.primaryStage.setResizable(false);
        initializeScene();
    }

    private void initializeScene() {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("FXML/Login.fxml"));
            SwitchScene.switchScene(loader, "Login");
    }

    public void createTableInDB(Tables t)
    {
        CreateTableQuery query = CreateTableQueryFactory.getQuery(t);
        query.execute();
    }

    public void createTables() {
        for (Tables t : Tables.values()) {
            createTableInDB(t);
        }

        Category.insertPresetCategoriesIntoDB();
    }
}

