package controller.handlers;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import model.domain.questions.Question;

public class RemoveQuestion implements EventHandler<ActionEvent> {
    private Controller controller;
    private TableView<Question> table;

    public RemoveQuestion(Controller controller, TableView<Question> table){
        this.controller = controller;
        this.table = table;
    }

    @Override
    public void handle(ActionEvent event) {
        Question question = getTable().getSelectionModel().getSelectedItem();
        getController().removeQuestion(question);
    }

    private Controller getController() {
        return controller;
    }

    private TableView<Question> getTable() {
        return table;
    }
}
