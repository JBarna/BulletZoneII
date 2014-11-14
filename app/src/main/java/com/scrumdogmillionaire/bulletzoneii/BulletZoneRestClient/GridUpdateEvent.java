package com.scrumdogmillionaire.bulletzoneii.BulletZoneRestClient;


public class GridUpdateEvent {
    int [][] grid;
    long timestamp;

    public GridUpdateEvent(int[][] grid, long timestamp) {
        this.grid = grid;
        this.timestamp = timestamp;
    }

    public int[][] getGrid() {
        return grid;
    }

    public long getTimestamp() {
        return timestamp;
    }
}