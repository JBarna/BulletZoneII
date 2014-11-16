package com.scrumdogmillionaire.bulletzoneii.LogicItems;

/**
 * Destructible Wall object
 *<p>Is a mapItem object used to hold the logic related information for a Destructible wall element
 */
public class DestructibleWall extends MapItem{
    /**
     * Constructor.
     * Creates a Destructible wall
     * @param i the integer representation of the Destructible Wall element
     */
    public DestructibleWall(int i) {
        attributes[MapItem.HEALTH] = getHealth(i);
    }

    /**
     * Returns the current health of the Destructible wall
     * @param i the integer representation of the Destructible Wall element
     * @return return the health.
     */
    public int getHealth(int i)
    {
        return i-1000;
    }
}
