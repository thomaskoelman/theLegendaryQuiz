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
        //check if input is valid, send only valid input to controller
        String question = getQuestionField().getText();
        ArrayList<String> answers = stringToArrayList(getStatementsArea().getText());
        Category category = getCategoryField().getValue();
        String feedback = getFeedbackField().getText();
        int points = Integer.parseInt(getPuntenField().getText());
        getController().saveQuestion(question, answers, category, feedback, points);
        getStage().close();
    }

    private ArrayList<String> stringToArrayList(String areaField){
        String text = getStatementsArea().getText();
        ArrayList<String> answers = new ArrayList<>();
        String[] antwoorden = text.split("\n");
        for (String s: antwoorden){
            answers.add(s);
        }
        return answers;
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
