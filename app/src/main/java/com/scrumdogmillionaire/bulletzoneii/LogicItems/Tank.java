package com.scrumdogmillionaire.bulletzoneii.LogicItems;

/**
 * Tank object
 *<p>Is a mapItem object used to hold the logic related information for a tank element
 */
public class Tank extends MapItem{
    /**
     * Constructor.
     * Creates a Tank and initializes attributes
     * @param i the integer representation of the tank element
     */
    public Tank(int i){
        attributes[MapItem.ID] = getId(i);
        attributes[MapItem.HEALTH] = getHealth(i);
        attributes[MapItem.DIRECTION] = getDir(i);
    }

    /**
     * Returns an integer that represents the current direction the tank is pointing
     * @param i - the integer representation of the tank element
     * @return - integer representation of tank direction
     */
    public int getDir(int i){return i% 10;}

    /**
     * Returns an integer that represents the health of the tank
     * @param i - the integer representation of the tank element
     * @return - integer representation of tank health
     */
    public int getHealth(int i){
       int ret = i/10;
       ret = ret%1000;
       return ret;
    }

    /**
     * Returns an integer that is the id of the tank
     * @param i - the integer representation of the tank element
     * @return - the tank id
     */
    public int getId(int i)
    {
       int ret = i/10000;
       ret=ret%1000;
       return ret;
    }
}

