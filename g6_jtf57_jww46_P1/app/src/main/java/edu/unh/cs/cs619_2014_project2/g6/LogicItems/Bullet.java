package edu.unh.cs.cs619_2014_project2.g6.LogicItems;

/**
 * Bullet object
 * <p>Is a mapItem object used to hold the logic related information for a Bullet element
 */
public class Bullet extends MapItem {
    /**
     * Constructor
     * Sets the Id and Damage attributes for the bullet
     * @param i - - integer representation of the Bullet element
     */
    public Bullet(int i, long usrID, int charType)
    {
        attributes[MapItem.ID] = getId(i);
        attributes[MapItem.DAMAGE] = getDamage(i);
        attributes[MapItem.TYPE] = getType(attributes[MapItem.ID], usrID, charType);
    }

    /**
     * Returns the an integer that is the Id of the tank who fired the bullet
     * @param i - integer representation of the Bullet element
     * @return - the id of firer
     */
    public int getId(int i)
    {
        int ret = i/1000;
        ret=ret%1000;
        return ret;
    }

    /**
     * Returns an integer that is the damage the bullet will do
     * @param i - integer representation of the Bullet element
     * @return - the damage of the bullet
     */
    public int getDamage(int i){
        int ret = i/10;
        ret = ret%1000;
        return ret;
    }

    /**
     * Returns an integer to represent the type of bullet
     * @param i - id of the tank that fire bullet
     * @param j - id of the user tank
     * @param c - the type of character
     * @return - the type of bullet
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
