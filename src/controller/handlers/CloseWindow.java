package controller.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class CloseWindow implements EventHandler<ActionEvent> {
    Stage stage;

    public CloseWindow(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent event) {
        getStage().close();
    }

    private Stage getStage(){
        return this.stage;
    }
}
