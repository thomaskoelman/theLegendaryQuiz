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
    public void quizEnds() {
       getQuiz().setState(getQuiz().getFinished());
    }

    private Quiz getQuiz(){
        return this.quiz;
    }
}
