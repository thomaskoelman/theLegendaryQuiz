package model.domain.questions;

import model.domain.categories.Category;

import java.util.ArrayList;

public class JaNee extends Question {

    public JaNee(String question, ArrayList<String> answers, Category category, String feedback, int points) {
        super(question, answers, category, feedback, points);
    }
}
