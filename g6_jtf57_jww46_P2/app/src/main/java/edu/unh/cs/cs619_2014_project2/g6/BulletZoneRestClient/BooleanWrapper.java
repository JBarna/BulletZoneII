package edu.unh.cs.cs619_2014_project2.g6.BulletZoneRestClient;

/**
 * Class that wraps a Boolean variable
 */
public class BooleanWrapper {

    private boolean bool;

    private long timeStamp;

    private boolean result;

    /**
     *Base constructor
     */
    public BooleanWrapper(){
    }

    /**
     * Constructor that takes a boolean and sets the bool variable to said oolen
     * @param bool - boolean we wish to set bool too
     */
    public BooleanWrapper(boolean bool){
        this.bool = bool;
    }

    /**
     * Used to set the bool variable
     * @param bool - - boolean we wish to set bool too
     */
    public void setBoolean(boolean bool){
        this.bool = bool;
    }

    /**
     * Use to return the bool variable
     * @return - return the bool variable
     */
    public boolean getBoolean(){
        return this.bool;
    }

    /**
     * Used to return the result variable
     * @return - return the result variable
     */
    public boolean getResult(){ return this.result; }

    /**
     * Used to return the timeStamp variable
     * @return - return the timeStamp variable
     */
    public long getTimeStamp(){
        return timeStamp;
    }

    /**
     * Used to set the timeStamp variable
     * @param timeStamp - long that we want to set the timeStamp too
     */
    public void setTimeStamp(long timeStamp){
        this.timeStamp = timeStamp;
    }
}
