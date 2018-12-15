package controller.handlers;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.domain.states.State;
import view.QuestionShower;

public class StartQuiz implements EventHandler<ActionEvent> {
    Controller controller;

    public StartQuiz(Controller controller){
        this.controller = controller;
    }

    @Override
    public void handle(ActionEvent event) {
        State state = getController().getState();
        if (getController().quizCanStart()){
            getController().getFirstQuestion();
            Stage stage = new Stage();
            QuestionShower questionShower = new QuestionShower(getController(), stage, state);
            Scene scene = new Scene(questionShower);
            stage.setScene(scene);
            stage.show();
        }

    }

    private Controller getController() {
        return controller;
    }
}
