package controller.handlers;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import model.domain.states.State;

public class AbortQuiz implements EventHandler<WindowEvent> {
    Controller controller;
    State state;

    public AbortQuiz(Controller controller, State state){
        this.controller = controller;
        this.state = state;
    }
    @Override
    public void handle(WindowEvent event) {
        getController().quizEnds(getState());
    }

    private Controller getController() {
        return controller;
    }

    private State getState(){
        return this.state;
    }
}
