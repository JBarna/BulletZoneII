package com.scrumdogmillionaire.bulletzoneii.BulletZoneRestClient;

/**
 * Created by JBarna on 11/8/2014.
 */
public class GridWrapper {

    private int [][] grid;

    private long timeStamp;

    public GridWrapper(){
    }

    public GridWrapper(int [] [] grid){
        this.grid = grid;
    }

    public void setGrid(int [] [] grid){
        this.grid = grid;
    }

    public int [][] getGrid(){
        return this.grid;
    }

    public long getTimeStamp(){
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp){
        this.timeStamp = timeStamp;
    }
}
