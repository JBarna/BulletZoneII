package edu.unh.cs.cs619_2014_project2.g6.LogicItems;

/**
 * Factory Class used to return appropriate mapItem object
 */
public class MapItemFactory{
    final static private EmptySpace es = new EmptySpace(0);
    final static private IndestructibleWall dw = new IndestructibleWall(1000);


    /**
     * Returns appropriated map item based on the integer representation of an element passed in
     * @param val -integer representation of element
     * @return - the mapItem associated with th integer representation of the element
     */
    public MapItem getItem(int val, long usrID, int character)
    {
       if(val==0)
       {
           return es;
       }
       else if(val==1000)
       {
            return dw;
       }
       else if(val>1000 && val<=2000)
       {
           return new DestructibleWall(val);
       }
       else if((val >= 2000000) && (val <= 3000000))
       {
           return new Bullet(val, usrID, character);
       }
       else if(val >= 10000000 && val <= 20000000)
       {
           return new Tank(val, usrID, character);
       }
        return null;
    }

}
