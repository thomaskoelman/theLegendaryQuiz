package model.domain.feedback;

public abstract class ScoreAlgorithm extends Score {
    private Score score;

    protected ScoreAlgorithm(Score score){
        this.score = score;
    }

    protected Score getScore(){
        return this.score;
    }
}
