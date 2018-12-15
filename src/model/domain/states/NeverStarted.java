package model.domain.states;

import model.domain.Quiz;

public class NeverStarted implements State {
    private Quiz quiz;

    public NeverStarted(Quiz quiz){
        this.quiz = quiz;
    }

    @Override
    public String toString() {
        return "NeverStarted";
    }

    @Override
    public String getMessage() {
        return "You never started this quiz before.";
    }

    @Override
    public boolean quizCanStart() {
        getQuiz().setState(getQuiz().getUnderway());
        return true;
    }

    @Override
    public void quizEnds(State state) {
    }

    private Quiz getQuiz(){
        return this.quiz;
    }
}
