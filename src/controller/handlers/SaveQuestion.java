package controller.handlers;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.domain.categories.Category;

public class SaveQuestion implements EventHandler<ActionEvent> {
    Stage stage;
    Controller controller;
    TextField questionField, puntenField, feedbackField;
    TextArea statementsArea;
    ComboBox<Category> categoryField;

    public SaveQuestion(Stage stage, Controller controller, TextField questionField, TextArea statementsArea, ComboBox<Category> categoryField, TextField puntenField, TextField feedbackField) {
        this.stage = stage;
        this.controller = controller;
        this.questionField = questionField;
        this.puntenField = puntenField;
        this.feedbackField = feedbackField;
        this.statementsArea = statementsArea;
        this.categoryField = categoryField;
    }

    @Override
    public void handle(ActionEvent event) {
        getController().saveQuestion();
    }

    //getters

    private Stage getStage(){
        return this.stage;
    }

    private Controller getController(){
        return this.controller;
    }

    public TextField getQuestionField() {
        return questionField;
    }

    public TextArea getStatementsArea() {
        return statementsArea;
    }

    public TextField getFeedbackField() {
        return feedbackField;
    }

    public TextField getPuntenField() {
        return puntenField;
    }

    public ComboBox<Category> getCategoryField() {
        return categoryField;
    }
}
