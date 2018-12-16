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
import model.domain.factories.QuestionFactory;
import model.domain.feedback.Feedback;
import model.domain.questions.JaNee;
import model.domain.questions.Question;
import model.domain.states.State;
import view.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Controller implements Subject {
    private Quiz quiz;
    private ArrayList<Observer> observers;
    private String message;
    private String question;
    private ArrayList<String> answers;
    private ObservableList<Category> categories;
    private ObservableList<Question> questions;
    private Iterator<Question> questionIterator;
    private Question currentQuestion;
    private ArrayList<Question> answersRemembered;
    private Category selectedCategory;
    private Question selectedQuestion;

    public Controller(){
        this.observers = new ArrayList<>();
        setQuiz();
        ObservableList<Category> categories = readCategories();
        setCategories(categories);
        ObservableList<Question> questions = readQuestions();
        setQuestions(questions);
        setMessage(getQuiz().getMessage());
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
        ArrayList<Question> shuffledList = new ArrayList<>(getQuestions());
        Collections.shuffle(shuffledList);
        this.questionIterator = shuffledList.iterator();
        this.answersRemembered = new ArrayList<>();
        Question question = getQuestionIterator().next();
        setCurrentQuestion(question);
        String phrase = question.getQuestion();
        ArrayList<String> answers = question.getAnswers();
        setQuestionAndAnswers(phrase, answers);
    }

    //let the questionshower display the next question, unless there is none. In the last case, end the quiz
    public void getNextQuestion(Stage stage){
        getAnswersRemembered().add(getCurrentQuestion());
        if (getQuestionIterator().hasNext()){
            Question question = getQuestionIterator().next();
            setCurrentQuestion(question);
            String phrase = question.getQuestion();
            ArrayList<String> answers = question.getAnswers();
            setQuestionAndAnswers(phrase, answers);
        } else {
            quizEnds(getQuiz().getFinished());
            String feedback = getQuiz().writeFeedback(getAnswersRemembered());
            setMessage(feedback);
            stage.close();
        }
    }

    //method that lets the quiz end when reaching last question or aborting quiz. sets state to finished
    public void quizEnds(State state){
        getQuiz().quizEnds(state);
    }

    //determine whether you can start another quiz based on your state: when you are already underway, you cannot start again
    public boolean quizCanStart(){
        return getQuiz().quizCanStart();
    }

    public State getState(){
        return getQuiz().getState();
    }

    //every time we hit the submit button during a quiz, we have to store the answer the user chose
    public void registerAnswer(String answer){
        getCurrentQuestion().setCheckedAnswer(answer);
    }

    //in the combobox of the category creator we have to list all main categories, so we need to retrieve them
    public ObservableList<MainCategory> getMainCategories(){
        return FXCollections.observableArrayList(getQuiz().getMainCategories());
    }

    //in the combobox of the question manager we have to list all possibilities of feedback, so we need to retrieve them
    public ObservableList<Feedback> getFeedbackTypes(){
        return FXCollections.observableArrayList(getQuiz().getFeedbackTypes());
    }

    public Feedback getFeedbackType(){
        return getQuiz().getFeedback();
    }

    //controller receives data from view and must overwrite a category in the database
    public void updateCategory(String name, String description, MainCategory mainCategory, String id){
        Category category = CategoryFactory.createCategory(name, description, mainCategory);
        ArrayList<Category> updatedCategories = getQuiz().updateCategory(category, id);
        setCategories(FXCollections.observableArrayList(updatedCategories));
        ArrayList<Question> updatedQuestions = getQuiz().updateQuestions(category, id);
        setQuestions(FXCollections.observableArrayList(updatedQuestions));
    }

    public void updateQuestion(String question, ArrayList<String> answers, Category category, String feedback, int points, String id) {
        Question question1 = QuestionFactory.createQuestion(question, answers, category, feedback, points);
        ArrayList<Question> updatedQuestions = getQuiz().updateQuestion(question1, id);
        setQuestions(FXCollections.observableArrayList(updatedQuestions));
    }
    //controller receives data from view and must create a category to add to the database
    public void saveCategory(String name, String description, MainCategory mainCategory){
        Category category = CategoryFactory.createCategory(name, description, mainCategory);
        ArrayList<Category> updatedCategories = getQuiz().saveCategory(category);
        setCategories(FXCollections.observableArrayList(updatedCategories));
    }

    public void removeQuestion(Question question){
        ArrayList<Question> updatedQuestions = getQuiz().removeQuestion(question);
        setQuestions(FXCollections.observableArrayList(updatedQuestions));
    }

    //the controller has to read the categories from the database before the category manager is initialised, so the table shows correctly
    private ObservableList<Category> readCategories(){
        return FXCollections.observableArrayList(getQuiz().readCategories());
    }

    //the controller has to read the questions from the database before the question manager is initialised, so the table shows correctly
    private ObservableList<Question> readQuestions(){
        return FXCollections.observableArrayList(getQuiz().readQuestions());
    }

    //controller receives data from view and must create a question to add to the database
    public void saveQuestion(String question, ArrayList<String> answers, Category category, String feedback, int points){
        Question questionObject = QuestionFactory.createQuestion(question, answers, category, feedback, points);
        ArrayList<Question> updatedQuestions = getQuiz().saveQuestion(questionObject);
        setQuestions(FXCollections.observableArrayList(updatedQuestions));
    }

    //when the feedback type is changed, the controller receives the new type and sets the quiz to that type
    public Feedback changeFeedbackType(Feedback feedback){
        getQuiz().setFeedback(feedback);
        return feedback;
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
            observer.update(getMessage(), getQuestion(), getAnswers(), getCategories(), getQuestions(), getSelectedCategory(), getSelectedQuestion());
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
        getQuiz().storeMessage(message);
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

    private Iterator<Question> getQuestionIterator(){
        return this.questionIterator;
    }

    private void setCurrentQuestion(Question question){
        this.currentQuestion = question;
    }

    private Question getCurrentQuestion(){
        return this.currentQuestion;
    }

    private ArrayList<Question> getAnswersRemembered(){
        return this.answersRemembered;
    }

    public void setSelectedCategory(Category category){
        this.selectedCategory = category;
        dataChanged();
    }

    private Category getSelectedCategory(){
        return this.selectedCategory;
    }

    public void setSelectedQuestion(Question question){
        this.selectedQuestion = question;
        dataChanged();
    }

    private Question getSelectedQuestion() {
        return selectedQuestion;
    }


}
