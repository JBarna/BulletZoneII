package com.scrumdogmillionaire.bulletzoneii.items;

/**
 * Created by jeep on 11/13/14.
 */
public class MapItemFactory{
    public MapItem makeItem(int val)
    {
       MapItem newItem = null;
       if(val==0)
       {
           return new EmptySpace(val);
       }
       else if(val==1000)
       {
            return new IndestructibleWall(val);
       }
       else if(val>1000 && val<=2000)
       {
           return new DestructibleWall(val);
       }
       else if((val >= 2000000) && (val <= 3000000))
       {
           return new Bullet(val);
       }
       else if(val >= 10000000 && val <= 20000000)
       {
           return new Tank(val);
       }
        return newItem;
    }
}
