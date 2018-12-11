package controller.observerPattern;

import java.util.ArrayList;

public interface Observer {
    void update(String message, String question, ArrayList<String> answers);
}
