package model.configurations;

import model.domain.Quiz;
import model.domain.feedback.Feedback;
import model.domain.feedback.FeedbackTypes;
import model.domain.feedback.NormalScoreCalculator;
import model.domain.states.State;

import java.io.*;
import java.util.Properties;

public class PropertyAccess {
    private static PropertyAccess uniqueInstance;
    String propFileName;
    Properties properties;
    InputStream inputStream;
    File file;

    private PropertyAccess() {
        propFileName = "props/quiz.properties";
        this.properties = readProperties();
        this.file = new File("props/quiz.properties");
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static PropertyAccess getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new PropertyAccess();
        }
        return uniqueInstance;
    }

    public void writeFeedbackToProperties(Feedback feedback) {
        if (getFeedback() == null) {
            properties.setProperty("feedback", feedback.toString());
        } else if (!getFeedback().toString().equals(feedback.toString())) {
            properties.setProperty("feedback", feedback.toString());
        }
        try {
            //FileOutputStream outputStream = new FileOutputStream(propFileName);
            properties.store(new FileOutputStream(propFileName), null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeStateToProperties(State state) {
        if (getState() != null) {
            if (!getState().equals(state.toString())) {
                properties.setProperty("state", state.toString());
                try {
                    //FileOutputStream outputStream = new FileOutputStream(propFileName);
                    properties.store(new FileOutputStream(propFileName), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void writeMessageToProperties(String message) {
        if (getLastResults() == null) {
            message = "You have never done this test before";
            properties.setProperty("lastResults", message);
        } else if (!getLastResults().equals(message)) {
            properties.setProperty("lastResults", message);
            try {
                //FileOutputStream outputStream = new FileOutputStream(propFileName);
                properties.store(new FileOutputStream(propFileName), null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Properties readProperties() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(propFileName);
            if (properties == null){
                properties = new Properties();
            }
            //properties.load(inputStream);

            if (!properties.containsKey("feedback")) {
                properties.setProperty("feedback", new NormalScoreCalculator().toString());
                properties.store(fileOutputStream, null);
            }
            if (!properties.containsKey("state")) {
                properties.setProperty("state", "NeverStarted");
                properties.store(fileOutputStream, null);
            }
            properties.load(inputStream);
        } catch (Exception e) {
            e.getMessage();
        } finally {
            return properties;
        }
    }

    public Feedback getFeedback() {
        try {
            String feedbackString = getProperties().getProperty("feedback");
            Feedback feedback = null;
            for (FeedbackTypes type : FeedbackTypes.values()) {
                if (type.getFeedback().toString().equals(feedbackString)) {
                    feedback = type.getFeedback();
                }
            }
            return feedback;
        } catch (Exception e) {
            return null;
        }
    }

    public String getState() {
        try {
            String state = getProperties().getProperty("state");
            return state;
        } catch (Exception e) {
            return "null";
        }
    }

    public String getLastResults() {
        try {
            String results = getProperties().getProperty("lastResults");
            return results;
        } catch (Exception e) {
            return "null";
        }
    }

    private Properties getProperties() {
        return this.properties;
    }

    /*private File getFile() {
        return file;
    }*/
}
