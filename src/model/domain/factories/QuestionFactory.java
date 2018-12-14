package model.domain.factories;

import model.domain.categories.Category;
import model.domain.questions.JaNee;
import model.domain.questions.Multiplechoice;
import model.domain.questions.Question;

import java.util.ArrayList;

public class QuestionFactory {
    public static Question createQuestion(String question, ArrayList<String> answers, Category category, String feedback, int points){
        boolean hasJa = false;
        boolean hasNee = false;
        boolean hasOnlyTwoElements = false;
        for (String answer: answers){
            String answerLC = answer.toLowerCase();
            if (answerLC.equals("ja") || answerLC.equals("yes")) hasJa = true;
            if (answerLC.equals("nee") || answerLC.equals("no")) hasNee = true;
        }
        if (answers.size() == 2) hasOnlyTwoElements = true;
        if (hasJa && hasNee && hasOnlyTwoElements){
            return new JaNee(question, answers, category, feedback, points);
        } else {
            return new Multiplechoice(question, answers, category, feedback, points);
        }
    }
}
