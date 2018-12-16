package view;


import controller.Controller;
import controller.handlers.ChangeCategory;
import controller.handlers.OpenNewCategoryCreator;
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

public class CategoryManager extends GridPane implements Observer {
    private TableView<Category> table;
    private Button addCategoryButton;

    public CategoryManager(Controller controller){
        //register to subject
        controller.registerObserver(this);

        //create all the objects that will be part of the manager
        //table
        this.table = new TableView<>();
        TableColumn<Category, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Category, String> descriptionColumn = new TableColumn<>("Description");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Category, String>("description"));
        this.table.getColumns().add(nameColumn);
        this.table.getColumns().add(descriptionColumn);
        this.table.setItems(controller.getCategories());
        //button
        this.addCategoryButton = new Button("Add category");

        //add objects to window
        add(this.table, 0, 1, 2, 6);
        add(this.addCategoryButton, 0, 11, 1, 1);

        //define actions of buttons
        this.addCategoryButton.setOnAction(new OpenNewCategoryCreator(controller));
        this.table.setOnMouseClicked(new ChangeCategory(this.table, controller));

        //layout
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        table.setPrefWidth(REMAINING);
        nameColumn.setPrefWidth(200);
        descriptionColumn.setPrefWidth(500);
    }


    //methods of the observer pattern
    @Override
    public void update(String message, String question, ArrayList<String> answers, ObservableList<Category> categories, ObservableList<Question> questions, Category category, Question selectedQuestion) {
        this.table.setItems(categories);
    }
}
