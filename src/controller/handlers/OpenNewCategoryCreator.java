package controller.handlers;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.CategoryCreator;

public class OpenNewCategoryCreator implements EventHandler<ActionEvent> {
    Controller controller;

    public OpenNewCategoryCreator(Controller controller){
        this.controller = controller;
    }
    @Override
    public void handle(ActionEvent event) {
        Stage stage = new Stage();
        CategoryCreator categoryCreator = new CategoryCreator(getController(), stage, event);
        Scene scene = new Scene(categoryCreator);
        stage.setScene(scene);
        stage.show();
    }

    private Controller getController() {
        return controller;
    }
}
