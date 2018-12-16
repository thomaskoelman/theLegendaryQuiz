package model.db;

import model.domain.categories.Category;
import model.domain.categories.MainCategory;
import model.domain.questions.Question;

import java.util.ArrayList;

public interface QuestionDB {

    ArrayList<Question> getQuestions();

    ArrayList<Question> addQuestion(Question question);

    ArrayList<Question> updateQuestion(Question question, String id);
}
