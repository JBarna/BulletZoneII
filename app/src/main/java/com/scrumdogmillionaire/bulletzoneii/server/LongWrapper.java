package com.scrumdogmillionaire.bulletzoneii.server;

/**
 * Created by JBarna on 11/8/2014.
 */
public class LongWrapper {

    private long l;

    private long timeStamp;

    public long getResult() {
        return this.result;
    }

    public void setResult(long result) {
        this.result = result;
    }

    private long result;

    public LongWrapper(){
    }

    public LongWrapper(long l){
        this.l = l;
    }

    public void setLong(long l){
        this.l = l;
    }

    public long getLong(){
        return this.l;
    }

    public long getTimeStamp(){
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp){
        this.timeStamp = timeStamp;
    }
}
