package edu.unh.cs.cs619_2014_project2.g6.LogicItems;

/**
 * Base class object that holds the logic related info for elements on the map
 */
public abstract class MapItem{
    String type;
    //these are the accessors for the attributes
    final static public int ID = 0;
    final static public int HEALTH = 1;
    final static public int DIRECTION = 2;
    final static public int DAMAGE = 3;
    final static public int TYPE = 4;

    int attributes[] = {0,0,0,0,0};

    /**
     * Returns the attribute at the requested index in the attributes array.
     * @param i - takes and integer 1-for ID, 2-for health, 3-for Direction, and 4-for health
     * @return - returns integer which is the attribute requested
     */
    public int getAttribute(int i){return attributes[i];}
}