package model.db;

import model.domain.categories.Category;
import model.domain.questions.Question;

import java.io.*;
import java.util.ArrayList;

public class QuestionTXT implements QuestionDB{
    ArrayList<Question> questions;
    File file;

    public QuestionTXT(){
        this.file = new File("files/questions.txt");
        this.questions = getQuestionsFromTXT();
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


    //getters

    public ArrayList<Question> getQuestions(){
        return this.questions;
    }

    @Override
    public ArrayList<Question> addQuestion(Question question) {
        if (question == null){
            throw new IllegalArgumentException("you cannot add a non-existent question");
        }
        getQuestions().add(question);
        saveQuestions();
        return getQuestions();
    }

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

    private File getFile(){
        return this.file;
    }
}
