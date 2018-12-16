package controller.handlers;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.domain.questions.Question;
import view.QuestionCreator;

public class ChangeQuestion implements EventHandler<MouseEvent> {
    TableView<Question> table;
    Controller controller;

    public ChangeQuestion(TableView<Question> table, Controller controller){
        this.table = table;
        this.controller = controller;
    }
    @Override
    public void handle(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
            Stage stage = new Stage();
            QuestionCreator questionCreator = new QuestionCreator(getController(), stage);
            Scene scene = new Scene(questionCreator);
            stage.setScene(scene);
            Question question = getTable().getSelectionModel().getSelectedItem();
            getController().setSelectedQuestion(question);
            stage.show();
        }
    }

    private TableView<Question> getTable() {
        return table;
    }

    private Controller getController() {
        return controller;
    }
}
