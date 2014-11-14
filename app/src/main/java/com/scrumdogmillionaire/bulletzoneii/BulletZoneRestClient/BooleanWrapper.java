package com.scrumdogmillionaire.bulletzoneii.BulletZoneRestClient;

/**
 * Created by JBarna on 11/8/2014.
 */
public class BooleanWrapper {

    private boolean bool;

    private long timeStamp;

    private boolean result;

    public BooleanWrapper(){
    }

    public BooleanWrapper(boolean bool){
        this.bool = bool;
    }

    public void setBoolean(boolean bool){
        this.bool = bool;
    }

    public boolean getBoolean(){
        return this.bool;
    }

    public boolean getResult(){ return this.result; }

    public long getTimeStamp(){
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp){
        this.timeStamp = timeStamp;
    }
}
