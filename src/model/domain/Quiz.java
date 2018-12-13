package model.domain;

import model.db.CategoryDB;
import model.db.CategoryTXT;
import model.db.QuestionDB;
import model.db.QuestionTXT;
import model.domain.categories.Category;
import model.domain.categories.MainCategory;
import model.domain.feedback.Feedback;
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

    public ArrayList<Category> readCategories() {
        return getCategoryDB().getCategories();
    }
}
