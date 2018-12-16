package model.db;

import model.domain.categories.Category;
import model.domain.questions.Question;

import java.io.*;
import java.util.ArrayList;

public class QuestionTXT implements QuestionDB{
    private static QuestionTXT uniqueInstance;
    ArrayList<Question> questions;
    File file;

    private QuestionTXT(){
        this.file = new File("files/questions.txt");
        this.questions = getQuestionsFromTXT();
    }

    public static synchronized QuestionTXT getInstance() {
        if (uniqueInstance == null){
            uniqueInstance = new QuestionTXT();
        }
        return uniqueInstance;
    }

    //fetch questions from the txt file
    private ArrayList<Question> getQuestionsFromTXT(){
        File file = getFile();
        try {
            if (file.length() == 0){
                return new ArrayList<>();
            }
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Question> questions = (ArrayList<Question>) ois.readObject();
            ois.close();
            return questions;
        }catch (Exception e){
            throw new DbException(e.getMessage(), e);
        }
    }

    //find the first corresponding question and overwrite it
    @Override
    public ArrayList<Question> updateQuestion(Question question, String id) {
        for (Question question1: getQuestions()){
            if (question1.getQuestion().equals(id)){
                int index = getQuestions().indexOf(question1);
                getQuestions().set(index, question);
            }
        }
        saveQuestions();
        return getQuestions();
    }

    @Override
    public ArrayList<Question> removeQuestion(Question question){
        int index = -1;
        while (getQuestions().contains(question)){
            index++;
            Question questionFromList = getQuestions().get(index);
            if (questionFromList.equals(question)){
                getQuestions().remove(questionFromList);
            }
        }
        saveQuestions();
        return getQuestions();
    }

    @Override
    public ArrayList<Question> updateQuestions(Category category, String id) {
        for (Question question: getQuestions()){
            if (question.getCategory().getName().equals(id)){
                question.setCategory(category);
            }
        }
        saveQuestions();
        return getQuestions();
    }

    //adds question to arraylist
    @Override
    public ArrayList<Question> addQuestion(Question question) {
        if (question == null){
            throw new IllegalArgumentException("you cannot add a non-existent question");
        }
        getQuestions().add(question);
        saveQuestions();
        return getQuestions();
    }


    //writes existing list of questions away to txt file
    private void saveQuestions(){
        try {
            FileOutputStream fos = new FileOutputStream(getFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(getQuestions());
            oos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //getters

    public ArrayList<Question> getQuestions(){
        return this.questions;
    }



    private File getFile(){
        return this.file;
    }
}
