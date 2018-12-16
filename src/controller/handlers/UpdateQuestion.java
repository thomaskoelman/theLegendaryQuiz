package controller.handlers;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.domain.categories.Category;

import java.util.ArrayList;

public class UpdateQuestion implements EventHandler<ActionEvent> {
    Stage stage;
    Controller controller;
    TextField questionField, puntenField, feedbackField;
    TextArea statementsArea;
    ComboBox<Category> categoryField;
    String id;

    public UpdateQuestion(Stage stage, Controller controller, TextField questionField, TextArea statementsArea, ComboBox<Category> categoryField, TextField puntenField, TextField feedbackField, String id) {
        this.stage = stage;
        this.controller = controller;
        this.questionField = questionField;
        this.puntenField = puntenField;
        this.feedbackField = feedbackField;
        this.statementsArea = statementsArea;
        this.categoryField = categoryField;
        this.id = id;
    }

    @Override
    public void handle(ActionEvent event) {
        String question = questionField.getText();
        ArrayList<String> answers = new ArrayList<>();
        for (String answer: statementsArea.getText().split("\n")){
            answers.add(answer);
        }
        Category category = categoryField.getValue();
        String feedback = feedbackField.getText();
        int points = Integer.parseInt(puntenField.getText());
        getController().updateQuestion(question, answers, category, feedback, points, getId());
        getStage().close();
    }

    private Controller getController() {
        return controller;
    }

    private String getId(){
        return id;
    }

    private Stage getStage(){
        return this.stage;
    }
}
