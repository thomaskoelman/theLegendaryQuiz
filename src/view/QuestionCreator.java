package view;


import controller.Controller;
import controller.handlers.CloseWindow;
import controller.handlers.SaveQuestion;
import controller.handlers.UpdateQuestion;
import controller.observerPattern.Observer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.domain.categories.Category;
import model.domain.questions.Question;

import java.util.ArrayList;

public class QuestionCreator extends GridPane implements Observer {
    private Button addQuestionButton, cancelButton, addStatementButton, removeStatementButton;
    private TextArea statementsArea;
    private TextField questionField, statementField, feedbackField, puntenField;
    private ComboBox<Category> categoryField;
    private Stage stage;
    private Controller controller;

    public QuestionCreator(Controller controller, Stage stage){
        this.stage = stage;
        this.controller = controller;
        //register to subject to receive data about selected question
        controller.registerObserver(this);

        //create objects that will be part of the window
        Label question = new Label("Question:");
        Label statements = new Label("Statements:");
        Label statement = new Label("Statement");
        Label category = new Label("Category:");
        Label feedback = new Label("Feedback:");
        Label punten = new Label("Points to gain:");
        this.questionField = new TextField();
        this.statementField = new TextField();
        this.feedbackField = new TextField();
        this.puntenField = new TextField();
        this.categoryField = new ComboBox<>();
        this.categoryField.setItems(controller.getCategories());
        this.statementsArea = new TextArea();
        this.statementsArea.setPrefRowCount(5);
        this.statementsArea.setEditable(false);
        Pane addRemove = new HBox();
        this.addStatementButton = new Button("add");
        this.removeStatementButton = new Button("remove");
        this.addQuestionButton = new Button("Save");
        this.addQuestionButton.isDefaultButton();
        this.cancelButton = new Button("Cancel");

        //adds objects to the window
        add(question,0,0,1,1);
        add(statement, 0, 1, 1, 1);
        add(statements,0, 2, 1, 1);
        add(category,0, 9, 1, 1);
        add(feedback, 0, 10, 1, 1);
        add(punten,0,11,1,1);
        add(this.questionField, 1, 0, 2, 1);
        add(this.statementField, 1, 1, 2, 1);
        add(this.statementsArea, 1, 2, 2, 5);
        addRemove.getChildren().add(this.addStatementButton);
        addRemove.getChildren().add(this.removeStatementButton);
        add(addRemove, 1, 8, 2, 1);
        add(this.categoryField,1, 9, 2, 1);
        add(this.feedbackField, 1, 10,2,1);
        add(this.puntenField, 1, 11, 1, 1);
        add(this.cancelButton, 0, 12, 1, 1);
        add(this.addQuestionButton, 1, 12, 2, 1);

        //define what the buttons do
        this.addStatementButton.setOnAction(new AddStatementToArea());
        this.removeStatementButton.setOnAction(new RemoveStatementFromArea());
        this.cancelButton.setOnAction(new CloseWindow(stage));
        this.addQuestionButton.setOnAction(new SaveQuestion(stage, controller, this.questionField, this.statementsArea, this.categoryField, this.puntenField, this.feedbackField));

        //layout
        this.setPrefHeight(350);
        this.setPrefWidth(700);
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
    }

    private void fillInFields(Question question){
        try{
            this.questionField.setText(question.getQuestion());
            String questionID = this.questionField.getText();
            String answers = "";
            for (String answer: question.getAnswers()){
                answers += answer + "\n";
            }
            this.statementsArea.setText(answers);
            this.categoryField.getSelectionModel().select(question.getCategory());
            this.feedbackField.setText(question.getFeedback());
            String points = "" + question.getPoints();
            this.puntenField.setText(points);
            this.addQuestionButton = new Button("Update");
            this.addQuestionButton.setOnAction(new UpdateQuestion(getStage(), getController(),this.questionField, this.statementsArea, this.categoryField, this.puntenField, this.feedbackField, questionID));
            add(this.addQuestionButton,1,12,2,1);
        } catch (NullPointerException e){
            //ignore nullpointer exceptions here, they don't harm the program.
        }
    }

    @Override
    public void update(String message, String question, ArrayList<String> answers, ObservableList<Category> categories, ObservableList<Question> questions, Category category, Question selectedQuestion) {
        fillInFields(selectedQuestion);
    }

    private Controller getController() {
        return controller;
    }

    private Stage getStage() {
        return stage;
    }

    /* unlike the other handlers, following handlers are part of view. They are no part of the controller
             because they contain no logic to be flushed to the model. Scope and context remain limited to current questioncreator
            */
    private class AddStatementToArea implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String statement = statementField.getText();
            if (statement != null && !statement.trim().isEmpty()){
                statementsArea.appendText(statement + "\n");
                statementField.clear();
            }
        }
    }

    private class RemoveStatementFromArea implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String statements = statementsArea.getText();
            String[] statementList = statements.split("\n");
            String selection = statementsArea.getSelectedText();
            String replacement = "";
            for (String statement: statementList){
                if (!statement.equals(selection)){
                    replacement += statement + "\n";
                }
            }
            statementsArea.replaceText(0, statements.length(), replacement);
        }

    }
}
