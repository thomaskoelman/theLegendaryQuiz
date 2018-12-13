package controller;

import controller.observerPattern.Observer;
import controller.observerPattern.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.domain.Quiz;
import model.domain.categories.Category;
import model.domain.categories.MainCategory;
import model.domain.factories.CategoryFactory;
import model.domain.questions.Question;
import view.*;

import javax.swing.*;
import java.util.ArrayList;

public class Controller implements Subject {
    private Quiz quiz;
    private ArrayList<Observer> observers;
    private String message;
    private String question;
    private ArrayList<String> answers;
    private ObservableList<Category> categories;
    private ObservableList<Question> questions;

    public Controller(){
        this.observers = new ArrayList<>();
        setQuiz();
        ObservableList<Category> categories = readCategories();
        setCategories(categories);
    }

    //all the initialisation of the application: creating the base panels and root
    public void init(Stage primaryStage){
        try{
            MessageShower messageShower = new MessageShower(this);
            CategoryManager categoryManager = new CategoryManager(this);
            QuestionManager questionManager = new QuestionManager(this);

            Group root = new Group();
            Scene scene = new Scene(root, 750, 400);

            BorderPane borderPane = new Navigator(messageShower, categoryManager, questionManager);
            borderPane.prefHeightProperty().bind(scene.heightProperty());
            borderPane.prefWidthProperty().bind(scene.widthProperty());

            root.getChildren().add(borderPane);
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();

            primaryStage.show();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //when we start a new round, the controller must retrieve the first question from model
    public void getFirstQuestion(){

    }

    //in the combobox of the category creator we have to list all main categories, so we need to retrieve them
    public ObservableList<MainCategory> getMainCategories(){
        return FXCollections.observableArrayList(getQuiz().getMainCategories());
    }

    //controller receives data from view and must create a category to add to the database
    public void saveCategory(String name, String description, MainCategory mainCategory){
        Category category = CategoryFactory.createCategory(name, description, mainCategory);
        ArrayList<Category> updatedCategories = getQuiz().saveCategory(category);
        setCategories(FXCollections.observableArrayList(updatedCategories));
    }

    //the controller has to read the categories from the database before the category manager is initialised, so the tabel shows correctly
    private ObservableList<Category> readCategories(){
        return FXCollections.observableArrayList(getQuiz().readCategories());
    }

    //controller receives data from view and must create a question to add to the database
    public void saveQuestion(){

    }


    //These are the methods that implement the observer pattern
    @Override
    public void registerObserver(Observer observer) {
        getObservers().add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        getObservers().remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer: getObservers()){
            observer.update(getMessage(), getQuestion(), getAnswers(), getCategories(), getQuestions());
        }
    }

    private void dataChanged(){
        notifyObservers();
    }

    //getters and setters

    public Quiz getQuiz() {
        return quiz;
    }

    private void setQuiz() {
        this.quiz = new Quiz();
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
        dataChanged();
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setQuestionAndAnswers(String question, ArrayList<String> answers){
        this.question = question;
        this.answers = answers;
        dataChanged();
    }

    public ObservableList<Category> getCategories() {
        return categories;
    }


    public void setCategories(ObservableList<Category> categories) {
        this.categories = categories;
        dataChanged();
    }

    public ObservableList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ObservableList<Question> questions) {
        this.questions = questions;
        dataChanged();
    }
}
