package Controllers;

import Models.User;
import Utils.SwitchScene;
import javafx.fxml.FXMLLoader;

abstract class BackToAnalyticsFilter {
    public void switchBackToAnalyticsFilter(User user) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("FXML/AnalyticsFilter.fxml"));
        AnalyticsFilterController controller = new AnalyticsFilterController(user);
        loader.setController(controller);
        SwitchScene.switchScene(loader,"AnalyticsFilter");
    }
}
