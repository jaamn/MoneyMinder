package Controllers;

import Models.User;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

public class MetricsController {

    @FXML
    private ProgressBar progressBar;

    @FXML
    private ProgressIndicator progressIndicator;

    private User user;

    public MetricsController(User user)
    {
        this.user = user;
    }

    public void initialize(){
        Task task = taskWorker(10);
        progressBar.progressProperty().unbind();
        progressIndicator.progressProperty().unbind();
        progressBar.progressProperty().bind(task.progressProperty());
        progressIndicator.progressProperty().bind(task.progressProperty());
        Thread thread = new Thread(task);
        thread.start();
    }

    private Task taskWorker(int seconds){
        return new Task(){
            protected Object call() throws Exception{
                for(int i = 0; i < seconds; i++){
                    updateProgress(i + 1, seconds);
                    Thread.sleep(1000);
                }
                return true;
            }
        };
    }
}
