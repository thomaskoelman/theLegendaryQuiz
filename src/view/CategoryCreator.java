package view;

import controller.Controller;
import controller.handlers.CloseWindow;
import controller.handlers.SaveCategory;
import controller.handlers.UpdateCategory;
import controller.observerPattern.Observer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.domain.categories.Category;
import model.domain.categories.MainCategory;
import model.domain.categories.SubCategory;
import model.domain.questions.Question;

import java.util.ArrayList;

public class CategoryCreator extends GridPane implements Observer {
    private Button confirmButton, cancelButton;
    private TextField nameField, descriptionField;
    private ComboBox<MainCategory> categoryField;

    public CategoryCreator(Controller controller, Stage stage, Event event){
        controller.registerObserver(this);

        //create objects that will be part of the window
        Label nameLabel = new Label("Name:");
        Label descriptionLabel = new Label("Description:");
        Label categoryLabel = new Label("Main Category:");
        this.nameField = new TextField();
        this.descriptionField = new TextField();
        this.categoryField = new ComboBox<>();
        this.categoryField.setItems(controller.getMainCategories());
        if (event instanceof ActionEvent){
            this.confirmButton = new Button("Add to list");
        } else if (event instanceof MouseEvent){
            this.confirmButton = new Button("Update category");
        }
        this.cancelButton = new Button("Cancel");

        //adds objects to the window
        add(nameLabel, 0, 0, 1, 1);
        add(descriptionLabel, 0, 1, 1, 1);
        add(categoryLabel, 0, 2, 1, 1);
        add(this.nameField, 1, 0, 1, 1);
        add(this.descriptionField, 1, 1, 1, 1);
        add(this.categoryField, 1, 2, 1, 1);
        add(this.cancelButton, 0, 3, 1, 1);
        add(this.confirmButton, 1, 3, 1, 1);

        //define what the buttons do
        if (event instanceof ActionEvent){
            this.confirmButton.setOnAction(new SaveCategory(controller, this.nameField, this.descriptionField, this.categoryField, stage));
        } else if (event instanceof MouseEvent){
            String categoryID = this.nameField.getText();
            this.confirmButton.setOnAction(new UpdateCategory(controller, this.nameField, this.descriptionField, this.categoryField, stage, categoryID));
        }
        this.cancelButton.setOnAction(new CloseWindow(stage));

        //layout
        this.setPrefHeight(150);
        this.setPrefWidth(600);
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        categoryLabel.setMinWidth(120);
        this.nameField.setPrefWidth(600);
        this.descriptionField.setPrefWidth(600);
    }

    private void fillInFields(Category category){
        try{
            this.nameField.setText(category.getName());
            this.descriptionField.setText(category.getDescription());
            if (category instanceof SubCategory){
                this.categoryField.getSelectionModel().select(((SubCategory) category).getMainCategory());
            } else {
                this.categoryField.getSelectionModel().select(null);
            }
        } catch (NullPointerException e){
            //ignore nullpointer exceptions here, they don't harm the program
        }

    }

    @Override
    public void update(String message, String question, ArrayList<String> answers, ObservableList<Category> categories, ObservableList<Question> questions, Category category, Question selectedQuestion) {
        fillInFields(category);
    }
}
