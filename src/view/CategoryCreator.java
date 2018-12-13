package view;

import controller.Controller;
import controller.handlers.CloseWindow;
import controller.handlers.SaveCategory;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.domain.categories.MainCategory;

public class CategoryCreator extends GridPane {
    private Button confirmButton, cancelButton;
    private TextField nameField, descriptionField;
    private ComboBox<MainCategory> categoryField;

    public CategoryCreator(Controller controller, Stage stage){

        //create objects that will be part of the window
        Label nameLabel = new Label("Name:");
        Label descriptionLabel = new Label("Description:");
        Label categoryLabel = new Label("Main Category:");
        this.nameField = new TextField();
        this.descriptionField = new TextField();
        this.categoryField = new ComboBox<>();
        this.categoryField.setItems(controller.getMainCategories());
        this.confirmButton = new Button("Add to list");
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
        this.confirmButton.setOnAction(new SaveCategory(controller, this.nameField, this.descriptionField, this.categoryField, stage));
        this.cancelButton.setOnAction(new CloseWindow(stage));

        //layout
        this.setPrefHeight(150);
        this.setPrefWidth(300);
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        categoryLabel.setPrefWidth(150);
    }
}