package model.db;

import model.domain.categories.Category;
import model.domain.categories.MainCategory;
import model.domain.questions.Question;

import java.util.ArrayList;

public interface QuestionDB {
    /*private CategoryDB categoryDB;
    private QuestionDB questionDB;

    public QuestionDB(){
        setCategories();
        setQuestions();
    }

    @Override
    public ArrayList<Category> saveCategory(Category category) {
        getCategoryDB().addCategory(category);
        return getCategoryDB().getCategories();
    }

    @Override
    public ArrayList<MainCategory> getMainCategories() {
        return getCategoryDB().getMainCategories();
    }

    public CategoryDB getCategoryDB() {
        return categoryDB;
    }

    private void setCategories(){
        this.categoryDB = new CategoryDB();
    }

    private void setQuestions(){
        this.questionDB = new QuestionDB();
    }*/
}
