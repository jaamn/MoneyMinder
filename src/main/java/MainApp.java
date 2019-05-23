
import Models.Tables;
import Utils.SQL.QueryFactory.CreateTableQueryFactory;
import Utils.SQL.QueryStatements.CreateTableQueries.*;
import Utils.SwitchScene;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

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

    private void createTables()
    {
        CreateTableQuery query = CreateTableQueryFactory.getQuery(Tables.Categories);
        query.execute();

        query = CreateTableQueryFactory.getQuery(Tables.Users);
        query.execute();
    }
}

