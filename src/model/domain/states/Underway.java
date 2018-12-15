package model.domain.states;

import model.domain.Quiz;

public class Underway implements State {
    private Quiz quiz;

    public Underway(Quiz quiz){
        this.quiz = quiz;
    }

    @Override
    public String toString() {
        return "Underway";
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public boolean quizCanStart() {
        return false;
    }

    @Override
    public void quizEnds(State state) {
       getQuiz().setState(state);
    }

    private Quiz getQuiz(){
        return this.quiz;
    }
}
