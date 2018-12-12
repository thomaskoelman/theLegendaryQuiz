package controller.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;

public class AddStatementToArea implements EventHandler<ActionEvent> {
    TextField statement;
    TextArea area;

    public AddStatementToArea(TextField statement, TextArea area){
        this.statement = statement;
        this.area = area;
    }
    @Override
    public void handle(ActionEvent event) {
        String statement = getStatement().getText();
        if (statement != null && !statement.trim().isEmpty()){
            getArea().appendText(statement + "\n");
            getStatement().clear();
        }
    }

    private TextField getStatement(){
        return this.statement;
    }

    private TextArea getArea(){
        return this.area;
    }
}
