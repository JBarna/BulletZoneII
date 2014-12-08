package edu.unh.cs.cs619_2014_project2.g6.LogicItems;

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
    public Tank(int i, long usrID, int character){
        attributes[MapItem.ID] = getId(i);
        attributes[MapItem.HEALTH] = getHealth(i);
        attributes[MapItem.DIRECTION] = getDir(i);
        attributes[MapItem.TYPE] = getType(attributes[MapItem.ID] , usrID, character);
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

    /**
     * Returns an integer to represent the type of tank
     * @param i - id of the tank
     * @param j - id of the user tank
     * @param c - the type of character
     * @return - the type of tank
     */
    public int getType(int i, long j, int c)
    {
        if(i == (int)j)
        {
            return c+1;
        }
        else
        {
            return 0;
        }

    }
}
