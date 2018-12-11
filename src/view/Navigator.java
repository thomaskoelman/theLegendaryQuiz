package view;

import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class Navigator extends BorderPane {
    public Navigator(MessageShower messageShower, CategoryManager categoryManager, QuestionManager questionManager) {
        //create a new tab panel
        TabPane tabPane = new TabPane();

        //create a little flowpanel which will show the messages and feedbacks
        FlowPane messageBox = new FlowPane(messageShower);
        messageBox.setAlignment(Pos.CENTER);

        //create the new tabs
        Tab testTab = new Tab("Test", messageBox);
        Tab categoriesTab = new Tab("Categories", categoryManager);
        Tab questionsTab = new Tab("Questions", questionManager);


        //add the tabs to the panel
        tabPane.getTabs().add(testTab);
        tabPane.getTabs().add(categoriesTab);
        tabPane.getTabs().add(questionsTab);

        //binds the tab panel to the border panel (see initialisation method of controller)
        this.setCenter(tabPane);
    }
}
