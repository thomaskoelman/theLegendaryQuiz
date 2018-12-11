package controller.observerPattern;

import java.util.ArrayList;

public interface Subject {
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers(String message, String question, ArrayList<String> answers);
}
