package com.scrumdogmillionaire.bulletzoneii.items;

/**
 * Created by jeep on 11/13/14.
 */
public class DestructibleWall extends MapItem{
    public DestructibleWall(int i)
    {
        attributes[0] = getHealth(i);
    }

    public int getHealth(int i)
    {
        return i-1000;
    }
}
