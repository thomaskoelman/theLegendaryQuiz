package model.domain.feedback;

import model.domain.categories.MainCategory;
import model.domain.categories.SubCategory;
import model.domain.questions.Question;

import java.util.ArrayList;

public interface Feedback {
    String writeFeedback(ArrayList<Question> questions, ArrayList<MainCategory> mainCategories, ArrayList<SubCategory> subCategories);
}
