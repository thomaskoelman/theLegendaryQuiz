package model.domain.states;

public interface State {

    String getMessage();

    boolean quizCanStart();

    void quizEnds(State state);
}
