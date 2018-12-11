package controller.handlers;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.QuestionShower;

public class StartQuiz implements EventHandler<ActionEvent> {
    Controller controller;

    public StartQuiz(Controller controller){
        this.controller = controller;
    }

    @Override
    public void handle(ActionEvent event) {
        getController().getFirstQuestion();
        QuestionShower questionShower = new QuestionShower(getController());
        Scene scene = new Scene(questionShower);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private Controller getController() {
        return controller;
    }
}
