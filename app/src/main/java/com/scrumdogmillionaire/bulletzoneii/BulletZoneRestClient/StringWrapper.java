package com.scrumdogmillionaire.bulletzoneii.BulletZoneRestClient;

/**
 * Created by JBarna on 11/13/2014.
 * Because the LEAVE method
 * returns a STRING, not a boolean
 */
public class StringWrapper {
    private long timeStamp;

    private String result;
    
    public StringWrapper(){
    }

    public StringWrapper(String result){
        this.result = result;
    }

    public void setString(String result){
        this.result = result;
    }

    public String getString(){
        return this.result;
    }

    public String getResult(){
        return this.result;
    }

    public long getTimeStamp(){
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp){
        this.timeStamp = timeStamp;
    }

}
