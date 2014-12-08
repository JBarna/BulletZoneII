package edu.unh.cs.cs619_2014_project2.g6.BulletZoneRestClient;

/**
 * Class that wraps a String variable
 */
public class StringWrapper {
    private long timeStamp;

    private String result;

    /**
     * Base Constructor
     */
    public StringWrapper(){
    }

    /**
     * Constructor that sets the result variable
     * @param result - string we want to set the result variable o
     */
    public StringWrapper(String result){
        this.result = result;
    }

    /**
     * Used to set the result variable
     * @param result - string we wish to set the result variable to
     */
    public void setString(String result){
        this.result = result;
    }

    /**
     * Used to get the result variable
     * @return - return the result variable
     */
    public String getString(){
        return this.result;
    }

    /**
     * Used to get the result variable
     * @return - returnt he result variable
     */
    public String getResult(){
        return this.result;
    }

    /**
     * Used to return the timeStampVariable
     * @return - return the timeStamp variable
     */
    public long getTimeStamp(){
        return timeStamp;
    }

    /**
     * Used to set the timeStamp variable
     * @param timeStamp - long we wish to set the timeStamp variable to 
     */
    public void setTimeStamp(long timeStamp){
        this.timeStamp = timeStamp;
    }

}
