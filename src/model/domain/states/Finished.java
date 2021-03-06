package model.domain.states;

import model.domain.Quiz;

public class Finished implements State {
    private Quiz quiz;

    public Finished(Quiz quiz){
        this.quiz = quiz;
    }

    @Override
    public String toString() {
        return "Finished";
    }

    @Override
    public String getMessage() {
        return getQuiz().getLastResults();
    }

    @Override
    public boolean quizCanStart() {
        getQuiz().setState(getQuiz().getUnderway());
        return true;
    }

    @Override
    public void quizEnds(State state) {
    }

    private Quiz getQuiz() {
        return quiz;
    }
}
