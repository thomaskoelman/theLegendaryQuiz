package model.domain.feedback;

import model.domain.categories.MainCategory;
import model.domain.categories.SubCategory;
import model.domain.questions.Question;

import java.util.ArrayList;

public class ChanceToLoseInstantlyAlgorithm extends ScoreAlgorithm {

    public ChanceToLoseInstantlyAlgorithm(Score score) {
        super(score);
    }

    @Override
    public String writeFeedback(ArrayList<Question> questions, ArrayList<MainCategory> mainCategories, ArrayList<SubCategory> subCategories) {
        return null;
    }
}
