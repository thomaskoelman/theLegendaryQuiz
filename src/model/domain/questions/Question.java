package model.domain.questions;

import model.domain.categories.Category;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Question implements Serializable {
    private String question;
    private ArrayList<String> answers;
    private Category category;
    private String feedback;
    private int points;

    protected Question(String question, ArrayList<String> answers, Category category, String feedback, int points){
        setQuestion(question);
        setAnswers(answers);
        setCategory(category);
        setFeedback(feedback);
        setPoints(points);
    }

    //setters

    private void setQuestion(String question) {
        if (question == null || question.trim().isEmpty()){
            throw new IllegalArgumentException("question must not be empty");
        }
        this.question = question;
    }

    public void setAnswers(ArrayList<String> answers) {
        if (answers == null || answers.isEmpty()){
            throw new IllegalArgumentException("answers should have one value at least");
        }
        this.answers = answers;
    }

    public void setCategory(Category category) {
        if (category == null){
            throw new IllegalArgumentException("Every question must belong to a category");
        }
        this.category = category;
    }

    public void setFeedback(String feedback) {
        if (feedback == null || feedback.trim().isEmpty()){
            throw new IllegalArgumentException("feedback must not be empty");
        }
        this.feedback = feedback;
    }

    public void setPoints(int points) {
        if (points < 0 || points > 10){
            throw new IllegalArgumentException("points must be between 0 and 10");
        }
        this.points = points;
    }

    //getters

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public Category getCategory() {
        return category;
    }

    public String getFeedback() {
        return feedback;
    }

    public int getPoints() {
        return points;
    }


}
