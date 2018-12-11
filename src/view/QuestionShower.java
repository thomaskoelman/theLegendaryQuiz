package view;

import controller.Controller;
import controller.observerPattern.Observer;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class QuestionShower extends GridPane implements Observer {
    private Label phrase;
    private ToggleGroup possibleAnswers;
    private Button submitAnswerButton;

    public QuestionShower(Controller controller){

        //create all the objects that will be part of the questionshower
        this.phrase = new Label();
        this.possibleAnswers = new ToggleGroup();
        this.submitAnswerButton = new Button("submit");

        //add the objects in the window
        updateComponents(this.phrase, this.possibleAnswers, this.submitAnswerButton);

        //layout
        this.setPrefHeight(300);
        this.setPrefWidth(750);
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
    }


    private void updateComponents(Label phrase, ToggleGroup possibleAnswers, Button submitAnswerButton){
        add(phrase, 0, 0, 1, 1);
    }

    //implements methods of the observer pattern
    @Override
    public void update(String message) {

    }
}
