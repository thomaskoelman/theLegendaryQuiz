package controller.handlers;


import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.domain.categories.Category;
import view.CategoryCreator;

public class ChangeCategory implements EventHandler<MouseEvent> {
    TableView<Category> table;
    Controller controller;

    public ChangeCategory(TableView<Category> table, Controller controller){
        this.table = table;
        this.controller = controller;
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
            Stage stage = new Stage();
            CategoryCreator categoryCreator = new CategoryCreator(getController(), stage, event);
            Scene scene = new Scene(categoryCreator);
            stage.setScene(scene);
            Category category = getTable().getSelectionModel().getSelectedItem();
            getController().setSelectedCategory(category);
            stage.show();
        }
    }

    private TableView<Category> getTable() {
        return table;
    }

    private Controller getController() {
        return controller;
    }
}
