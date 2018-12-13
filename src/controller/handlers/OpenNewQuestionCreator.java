package controller.handlers;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.CategoryCreator;
import view.QuestionCreator;

public class OpenNewQuestionCreator implements EventHandler<ActionEvent> {
    Controller controller;

    public OpenNewQuestionCreator(Controller controller){
        this.controller = controller;
    }

    @Override
    public void handle(ActionEvent event) {
        Stage stage = new Stage();
        QuestionCreator questionCreator = new QuestionCreator(getController(), stage);
        Scene scene = new Scene(questionCreator);
        stage.setScene(scene);
        stage.setWidth(700);
        stage.setHeight(450);
        stage.show();
    }

    private Controller getController() {
        return controller;
    }
}
