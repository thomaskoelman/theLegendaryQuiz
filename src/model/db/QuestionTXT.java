package model.db;

import model.domain.questions.Question;

import java.util.ArrayList;

public class QuestionTXT implements QuestionDB{
    ArrayList<Question> questions;

    public ArrayList<Question> getQuestions(){
        return this.questions;
    }
}
