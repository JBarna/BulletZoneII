package com.scrumdogmillionaire.bulletzoneii.BulletZoneRestClient;

/**
 * Class that wraps a Boolean variable
 */
public class GridWrapper {

    private int [][] grid;

    private long timeStamp;

    /**
     * Base Constructor
     */
    public GridWrapper(){
    }

    /**
     * Constructor where grid variable is set
     * @param grid - 2d array toet the grid variable to
     */
    public GridWrapper(int [] [] grid){
        this.grid = grid;
    }

    /**
     * Used to set the grid variable
     * @param grid - 2d int array to set the grid variable to
     */
    public void setGrid(int [] [] grid){
        this.grid = grid;
    }

    /**
     * Used to get the grid variable
     * @return - return the grid variable
     */
    public int [][] getGrid(){
        return this.grid;
    }

    /**
     * Used to get the timeStamp variable
     * @return
     */
    public long getTimeStamp(){
        return timeStamp;
    }

    /**
     * Used to set the timestamp variable
     * @param timeStamp - long we wish to set the timestamp variable to
     */
    public void setTimeStamp(long timeStamp){
        this.timeStamp = timeStamp;
    }
}
