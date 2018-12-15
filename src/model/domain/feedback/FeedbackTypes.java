package model.domain.feedback;

public enum FeedbackTypes {
    SCORE (new NormalScoreCalculator()),
    TIPS (new Tips());

    Feedback feedback;

    FeedbackTypes(Feedback feedback){
        this.feedback = feedback;
    }

    public Feedback getFeedback(){
        return this.feedback;
    }
}
