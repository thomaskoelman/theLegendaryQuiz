package controller.observerPattern;

import javafx.collections.ObservableList;
import model.domain.categories.Category;
import model.domain.questions.Question;

import java.util.ArrayList;

public interface Observer {
    void update(String message, String question, ArrayList<String> answers, ObservableList<Category> categories, ObservableList<Question> questions);
}
