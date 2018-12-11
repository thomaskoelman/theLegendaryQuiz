package application;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    //Makes the controller which will handle everything. he will even do the initialisation!
    @Override
    public void start(Stage primaryStage){
        Controller controller = new Controller();
        controller.init(primaryStage);
    }
}
