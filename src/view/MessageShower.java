package view;


import controller.Controller;
import controller.handlers.StartQuiz;
import controller.observerPattern.Observer;
import controller.observerPattern.Subject;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.domain.categories.Category;
import model.domain.questions.Question;

import java.util.ArrayList;

public class MessageShower extends GridPane implements Observer {
    private Subject controller;
    private Button startQuizButton;
    private Label message;

    public MessageShower(Controller controller){
        //register to subject
        this.controller = controller;
        controller.registerObserver(this);

        //create all the objects that will be part of the messageshower
        this.startQuizButton = new Button("Start quiz!");
        this.message = new Label();

        //add the objects in the window
        add(this.startQuizButton, 0, 2, 2, 1);
        add(this.message, 1, 0, 1, 1);

        //add workings of the buttons
        this.startQuizButton.setOnAction(new StartQuiz(controller));

        //layout
        setHalignment(this.startQuizButton, HPos.CENTER);
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    //the message needs to change dynamically to show feedback and states about the quiz
    private void setMessage(String message){
        getChildren().remove(this.message);
        this.message = new Label(message);
        add(this.message, 1,0, 1,1);
    }



    //methods of the observer pattern
    @Override
    public void update(String message, String question, ArrayList<String> answers, ObservableList<Category> categories, ObservableList<Question> questions) {
        setMessage(message);
    }

}
