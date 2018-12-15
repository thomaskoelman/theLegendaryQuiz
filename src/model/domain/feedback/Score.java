package model.domain.feedback;

import model.domain.questions.Question;

import java.util.ArrayList;

public abstract class Score implements Feedback{
    private ArrayList<Integer> points;

   protected void setPoints(ArrayList<Question> questions){

   }

    protected ArrayList<Integer> getPoints(){
        return points;
    }

    @Override
    public String toString() {
        return "Score";
    }
}
