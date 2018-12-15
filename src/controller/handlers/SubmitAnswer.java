package controller.handlers;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class SubmitAnswer implements EventHandler<ActionEvent> {
    private Controller controller;
    private Stage stage;
    ToggleGroup answers;

    public SubmitAnswer(Controller controller, Stage stage, ToggleGroup answers){
        this.stage = stage;
        this.controller = controller;
        this.answers = answers;
    }
    @Override
    public void handle(ActionEvent event) {
        String answer = (String) getAnswers().getSelectedToggle().getUserData();
        getController().registerAnswer(answer);
        getController().getNextQuestion(getStage());
    }

    private Controller getController(){
        return this.controller;
    }

    private Stage getStage(){
        return this.stage;
    }

    private ToggleGroup getAnswers(){
        return this.answers;
    }
}
