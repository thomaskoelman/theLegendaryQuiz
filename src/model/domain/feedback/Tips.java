package model.domain.feedback;

import model.domain.categories.MainCategory;
import model.domain.categories.SubCategory;
import model.domain.questions.Question;

import java.util.ArrayList;

public class Tips implements Feedback {

    @Override
    public String writeFeedback(ArrayList<Question> questions, ArrayList<MainCategory> mainCategories, ArrayList<SubCategory> subCategories) {
        String feedback = "";
        for (Question question: questions){
            if (!question.getCheckedAnswer().equals(question.getRightAnswer())){
                feedback += question.getFeedback() + "\n";
            }
        }
        return feedback;
    }

    @Override
    public String toString() {
        return "Tips";
    }
}
