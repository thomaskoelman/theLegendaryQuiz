package model.domain;

import model.db.CategoryDB;
import model.db.CategoryTXT;
import model.db.QuestionDB;
import model.db.QuestionTXT;
import model.domain.categories.Category;
import model.domain.categories.MainCategory;
import model.domain.categories.SubCategory;
import model.domain.feedback.Feedback;
import model.domain.feedback.NormalScoreCalculator;
import model.domain.feedback.Tips;
import model.domain.questions.Question;
import model.domain.states.State;

import java.util.ArrayList;

//the quiz class oversees everything of model, this is the facade
public class Quiz {
    private State state;
    private Feedback feedback;
    private CategoryDB categoryDB;
    private QuestionDB questionDB;

    public Quiz(){
        this.categoryDB = new CategoryTXT();
        this.questionDB = new QuestionTXT();
        this.feedback = new Tips();
    }

    public ArrayList<Category> saveCategory(Category category){
        return getCategoryDB().addCategory(category);
    }

    private CategoryDB getCategoryDB(){
        return this.categoryDB;
    }

    private QuestionDB getQuestionDB(){
        return this.questionDB;
    }

    public ArrayList<MainCategory> getMainCategories() {
        return getCategoryDB().getMainCategories();
    }

    private ArrayList<SubCategory> getSubCategories() {
        return getCategoryDB().getSubCategories();
    }

    public ArrayList<Category> readCategories() {
        return getCategoryDB().getCategories();
    }

    public ArrayList<Question> readQuestions(){
        return getQuestionDB().getQuestions();
    }

    public ArrayList<Question> saveQuestion(Question question) {
        return getQuestionDB().addQuestion(question);
    }

    public String writeFeedback(ArrayList<Question> questions){
        return getFeedback().writeFeedback(questions, getMainCategories(), getSubCategories());
    }

    private Feedback getFeedback(){
        return this.feedback;
    }
}
