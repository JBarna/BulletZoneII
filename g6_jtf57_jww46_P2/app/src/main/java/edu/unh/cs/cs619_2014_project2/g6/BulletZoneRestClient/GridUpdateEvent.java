package edu.unh.cs.cs619_2014_project2.g6.BulletZoneRestClient;

/**
 * a class wrapper that wraps a message (a grid and a timestamp) that needs to be passed from
 * Publisher (grid poller) to subscriber (main activity's onUpdateGrid).(From Piazza)
 */
public class GridUpdateEvent {
    int [][] grid;
    long timestamp;
    String data;

    /**
     * Constructor sets the grid and time stamp variables
     * @param grid - 2d integer array we want to set the grid variable to
     * @param timestamp - long to set the time stamp to
     */
    public GridUpdateEvent(int[][] grid, long timestamp) {
        this.grid = grid;
        this.timestamp = timestamp;
    }



    /**
     * Used to get the grid variable
     * @return - return the grid variable
     */
    public int[][] getGrid() {
        return grid;
    }

    /**
     * Used to get the timeStamp variable
     * @return - return the timestamp variable
     */
    public long getTimestamp() {
        return timestamp;
    }
}