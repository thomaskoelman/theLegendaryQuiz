package model.configurations;

import model.domain.feedback.Feedback;
import model.domain.feedback.FeedbackTypes;
import model.domain.feedback.NormalScoreCalculator;
import model.domain.states.State;
import model.domain.states.StateTypes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertyAccess {
    File file;
    Properties properties;

    public PropertyAccess(){
        this.file = new File("files/quiz.properties");
        this.properties = readProperties();
    }

    public void writeFeedbackToProperties(Feedback feedback){
        Properties properties = readProperties();
        properties.setProperty("feedback", feedback.toString());
        try{
            FileOutputStream outputStream = new FileOutputStream(getFile());
            properties.store(outputStream, null);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void writeStateToProperties(State state){
        Properties properties = readProperties();
        properties.setProperty("state", state.toString());
        try{
            FileOutputStream outputStream = new FileOutputStream(getFile());
            properties.store(outputStream, null);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void writeMessageToProperties(String message){
        Properties properties = readProperties();
        properties.setProperty("lastResults", message);
        try {
            FileOutputStream outputStream = new FileOutputStream(getFile());
            properties.store(outputStream, null);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private Properties readProperties(){
        Properties properties = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream(getFile());
            properties.load(inputStream);
            if (!properties.containsKey("feedback")){
                properties.setProperty("feedback", new NormalScoreCalculator().toString());
                FileOutputStream outputStream = new FileOutputStream(getFile());
                properties.store(outputStream, null);
            }
            if (!properties.containsKey("state")){
                properties.setProperty("state", "NeverStarted");
                FileOutputStream outputStream = new FileOutputStream(getFile());
                properties.store(outputStream, null);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            return properties;
        }
    }

    public Feedback getFeedback(){
        String feedbackString = getProperties().getProperty("feedback");
        Feedback feedback = null;
        for (FeedbackTypes type: FeedbackTypes.values()){
            if (type.getFeedback().toString().equals(feedbackString)){
                feedback = type.getFeedback();
            }
        }
        return feedback;
    }

    public String getState(){
        String state = getProperties().getProperty("state");
        return state;
    }

    public String getLastResults(){
        String results = getProperties().getProperty("lastResults");
        return results;
    }

    private Properties getProperties(){
        return this.properties;
    }

    private File getFile() {
        return file;
    }
}
