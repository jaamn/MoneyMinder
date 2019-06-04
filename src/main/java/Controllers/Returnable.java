package Controllers;

import Models.User;
import Utils.SwitchScene;
import javafx.fxml.FXMLLoader;

public class Returnable{
    public void returnToPage(User user, String fxml, String title, String returnPage) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource(fxml));
        if (returnPage.equals("analytics")) {
            AnalyticsFilterController controller = new AnalyticsFilterController(user);
            loader.setController(controller);
        }
        else if(returnPage.equals("main")) {
            MainMenuController controller = new MainMenuController(user);
            loader.setController(controller);
        }
        else{
            System.err.println("Can't return");
        }
        SwitchScene.switchScene(loader,title);
    }
}
