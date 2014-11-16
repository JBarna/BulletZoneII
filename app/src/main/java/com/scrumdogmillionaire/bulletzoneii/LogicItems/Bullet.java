package com.scrumdogmillionaire.bulletzoneii.LogicItems;

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
    public Bullet(int i)
    {
        attributes[MapItem.ID] = getId(i);
        attributes[MapItem.DAMAGE] = getDamage(i);
        type = "bullet";
    }

    /**
     * Returns the an integer that is the Id of the tank who fired the bullet
     * @param i - integer representation of the Bullet element
     * @return - the id of firer
     */
    public int getId(int i)
    {
        int ret = i/10000;
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

}
