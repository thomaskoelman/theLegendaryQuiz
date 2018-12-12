package view;

import controller.Controller;
import controller.handlers.OpenNewQuestionCreator;
import controller.observerPattern.Observer;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.domain.categories.Category;
import model.domain.questions.Question;

import java.util.ArrayList;

public class QuestionManager extends GridPane implements Observer {
    private Controller controller;
    private TableView<Question> table;
    private Button addQuestionButton;

    public QuestionManager(Controller controller){
        //registers to subject to receive data
        this.controller = controller;
        controller.registerObserver(this);

        //create all the objects that will be part of the manager
        //table
        this.table = new TableView<>();
        TableColumn<Question, String> phrasingColumn = new TableColumn<>("Question");
        TableColumn<Question, String> categoryColumn = new TableColumn<>("Category");
        phrasingColumn.setCellValueFactory(new PropertyValueFactory<Question, String>("phrase"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Question, String>("categoryName"));
        this.table.getColumns().add(phrasingColumn);
        this.table.getColumns().add(categoryColumn);
        this.table.setItems(controller.getQuestions());
        //button
        this.addQuestionButton = new Button("Add question");

        //add objects to window
        add(this.table, 0, 1, 2, 6);
        add(this.addQuestionButton, 0, 11, 1, 1);

        //add workings of the buttons
        this.addQuestionButton.setOnAction(new OpenNewQuestionCreator(controller));

        //layout
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        table.setPrefWidth(REMAINING);
        phrasingColumn.setPrefWidth(600);
        categoryColumn.setPrefWidth(150);
    }

    @Override
    public void update(String message, String question, ArrayList<String> answers, ObservableList<Category> categories, ObservableList<Question> questions) {
        this.table.setItems(questions);
    }
}
