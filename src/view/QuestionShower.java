package view;

import controller.Controller;
import controller.observerPattern.Observer;
import controller.observerPattern.Subject;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class QuestionShower extends GridPane implements Observer {
    private Subject controller;
    private Label phrase;
    private ToggleGroup possibleAnswers;
    private Button submitAnswerButton;

    public QuestionShower(Controller controller){
        //register to the controller as an observer
        this.controller = controller;
        controller.registerObserver(this);

        //create all the objects that will be part of the questionshower
        this.phrase = new Label();
        this.possibleAnswers = new ToggleGroup();
        this.submitAnswerButton = new Button("submit");

        //add the objects in the window
        controller.notifyObservers();

        //assign the actions to the buttons and other interactions
        this.submitAnswerButton.setOnAction(controller.submitAnswer());

        //layout
        this.setPrefHeight(300);
        this.setPrefWidth(750);
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
    }

    //updates all components for the appropriate question and locates them in the window
    private void updateComponents(String phrase, ArrayList<String> possibleAnswers){
        this.getChildren().clear();
        int index = 0;
        this.phrase = new Label(phrase);
        add(this.phrase, 0, index, 1, 1);
        ToggleGroup radiobuttons = new ToggleGroup();
        for (String answer: possibleAnswers){
            index++;
            RadioButton button = new RadioButton(answer);
            button.setUserData(answer);
            button.setToggleGroup(radiobuttons);
            add(button, 0, index, 1, 1);
        }
        this.possibleAnswers = radiobuttons;
        index++;
        add(this.submitAnswerButton, 0, index, 1, 1);
    }

    //implements methods of the observer pattern
    @Override
    public void update(String message, String question, ArrayList<String> answers) {
        updateComponents(question, answers);
    }
}
