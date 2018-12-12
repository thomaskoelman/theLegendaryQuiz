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
        CategoryCreator categoryCreator = new CategoryCreator(getController());
        Scene scene = new Scene(categoryCreator);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private Controller getController() {
        return controller;
    }
}
