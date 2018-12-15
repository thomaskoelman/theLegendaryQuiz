package controller.handlers;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import model.domain.feedback.Feedback;

public class ChangeFeedbackType implements EventHandler<ActionEvent> {
    ComboBox<Feedback> feedbackComboBox;
    Controller controller;

    public ChangeFeedbackType(ComboBox<Feedback> comboBox, Controller controller){
        this.feedbackComboBox = comboBox;
        this.controller = controller;
    }

    @Override
    public void handle(ActionEvent event) {
        Feedback feedback = getFeedbackComboBox().getValue();
        getFeedbackComboBox().getSelectionModel().select(feedback);
        getController().changeFeedbackType(feedback);
    }

    private ComboBox<Feedback> getFeedbackComboBox() {
        return feedbackComboBox;
    }

    private Controller getController() {
        return controller;
    }
}
