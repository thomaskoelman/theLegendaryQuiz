package view;


import controller.Controller;
import controller.observerPattern.Observer;
import controller.observerPattern.Subject;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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

        //add workings of the button
        this.startQuizButton.setOnAction(controller.startQuiz());

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
        this.message = new Label(message);
    }


    //methods of the observer pattern
    @Override
    public void update(String message) {
        setMessage(message);
    }

}
