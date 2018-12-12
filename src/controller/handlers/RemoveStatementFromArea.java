package controller.handlers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class RemoveStatementFromArea implements EventHandler<ActionEvent> {
    private javafx.scene.control.TextArea area;

    public RemoveStatementFromArea(TextArea area){
        this.area = area;
    }
    @Override
    public void handle(ActionEvent event) {
        String statements = getArea().getText();
        String[] statementList = statements.split("\n");
        String selection = getArea().getSelectedText();
        String replacement = "";
        for (String statement: statementList){
            if (!statement.equals(selection)){
                replacement += statement + "\n";
            }
        }
        getArea().replaceText(0, statements.length(), replacement);
    }

    private javafx.scene.control.TextArea getArea(){
        return this.area;
    }
}
