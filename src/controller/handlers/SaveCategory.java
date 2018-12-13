package controller.handlers;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.domain.categories.Category;
import model.domain.categories.MainCategory;

public class SaveCategory implements EventHandler<ActionEvent> {
    private Controller controller;
    private TextField nameField, descriptionField;
    private ComboBox<MainCategory> categoryField;
    private Stage stage;

    public SaveCategory(Controller controller, TextField nameField, TextField descriptionField, ComboBox<MainCategory> categoryField, Stage stage) {
        this.controller = controller;
        this.nameField = nameField;
        this.descriptionField = descriptionField;
        this.categoryField = categoryField;
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent event) {
        String name = this.nameField.getText();
        String description = this.descriptionField.getText();
        MainCategory mainCategory = this.categoryField.getValue();
        getController().saveCategory(name, description, mainCategory);
        getStage().close();
    }

    private Controller getController(){
        return this.controller;
    }
    private Stage getStage(){
        return this.stage;
    }
}
