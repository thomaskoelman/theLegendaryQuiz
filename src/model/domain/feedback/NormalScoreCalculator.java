package model.domain.feedback;

import model.domain.categories.Category;
import model.domain.categories.MainCategory;
import model.domain.categories.SubCategory;
import model.domain.questions.Question;

import java.util.ArrayList;

public class NormalScoreCalculator extends Score {

    //first display your total score, then print results per main category
    @Override
    public String writeFeedback(ArrayList<Question> questions, ArrayList<MainCategory> mainCategories, ArrayList<SubCategory> subCategories) {
        /*HashMap<Category, Integer> yourScores = getYourScores(questions);
        HashMap<Category, Integer> totalScores = getTotalScores(questions);*/
        String feedback = totalScore(questions);
        for (MainCategory mainCategory: mainCategories){
            feedback += mainCategoryToString(questions, mainCategory, subCategories);
        }
        return feedback;
    }

    //makes no difference between categories and shows final score result
    private String totalScore(ArrayList<Question> questions){
        int points = 0;
        int total = 0;
        for (Question question: questions){
            total += question.getPoints();
            if (question.getCheckedAnswer().equals(question.getRightAnswer())){
                points += question.getPoints();
            }
        }
        String feedback = "";
        if (points == total){
            feedback += "Awesome! You answered everything correctly.\n";
        }
        return feedback += "These are your scores: " + points + "/" + total + "\n";
    }

    //display points gained of your main category specified, results are incremented also by the main category's subcategories' results
    private String mainCategoryToString(ArrayList<Question> questions, MainCategory mainCategory, ArrayList<SubCategory> subCategories){
        String feedback = "";
        feedback += mainCategoryOnly(questions, mainCategory);
        feedback += subCategoriesToString(questions, mainCategory, subCategories);
        return feedback;
    }

    //calculates the ratio of gained points over total points of specified main category.
    //points of main category and of all subcategories belonging to this maincategory are included.
    private String mainCategoryOnly(ArrayList<Question> questions, MainCategory mainCategory){
        int points = 0;
        int total = 0;
        for (Question question: questions){
            if (mainCategory.toString().equals(question.getCategory().toString())){
                total += question.getPoints();
                if (question.getCheckedAnswer().equals(question.getRightAnswer())){
                    points += question.getPoints();
                }
            } else if (question.getCategory() instanceof SubCategory){
                if (mainCategory.toString().equals(((SubCategory) question.getCategory()).getMainCategory().toString())){
                    total += question.getPoints();
                    if (question.getCheckedAnswer().equals(question.getRightAnswer())){
                        points += question.getPoints();
                    }
                }
            }
        }
        String feedback = "";
        if (total !=0){
            feedback = mainCategory.getName() + ": " + points + "/" + total + "\n";
        }
        return feedback;
    }

    //calculates ratio of gained points over total points of every individual subcategory belonging to given maincategory
    private String subCategoriesToString(ArrayList<Question> questions, MainCategory mainCategory, ArrayList<SubCategory> subCategories){
        String feedback = "";
        for (SubCategory subCategory: subCategories){
            if (subCategory.getMainCategory().toString().equals(mainCategory.toString())){
                feedback += subCategoryOnly(questions, subCategory);
            }
        }
        return feedback;
    }

    //display results for the single subcategory specified
    private String subCategoryOnly(ArrayList<Question> questions, SubCategory subCategory){
        int points = 0;
        int total = 0;
        for (Question question: questions){
            Category category = question.getCategory();
            if (category.toString().equals(subCategory.toString())){
                total += question.getPoints();
                if (question.getCheckedAnswer().equals(question.getRightAnswer())){
                    points += question.getPoints();
                }
            }
        }
        String feedback = "";
        if (total != 0){
            feedback = subCategory.getName() + ": " + points + "/" + total + "\n";
        }
        return feedback;
    }

}
