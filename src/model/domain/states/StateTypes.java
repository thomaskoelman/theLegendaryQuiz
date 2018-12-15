package model.domain.states;

public enum StateTypes {
    NEVERSTARTED("NeverStarted"),
    UNDERWAY("Nnderway"),
    FINISHED("Finished");

    String state;

    StateTypes(String state){
        this.state = state;
    }

    public String getState(){
        return this.state;
    }
}
