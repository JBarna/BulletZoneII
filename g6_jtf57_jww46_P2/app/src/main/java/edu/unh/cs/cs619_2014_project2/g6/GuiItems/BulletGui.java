package edu.unh.cs.cs619_2014_project2.g6.GuiItems;

import android.content.Context;

import edu.unh.cs.cs619_2014_project2.g6.LogicItems.MapItem;
import edu.unh.cs.cs619_2014_project2.g6.R;

/**
 * GuiItem object used to display a bullet item on the map
 */
public class BulletGui extends GuiItem {
    /**
     * Base Constructor
     */
    public BulletGui(MapItem bullet)
    {
        setDisplay(bulletType(bullet.getAttribute(MapItem.TYPE)));
    }

    /**
     * Returns the appropraite image representation of bullet based on the integer representation
     * of the bullet
     * @param i - integer attribute that represents the type of bullet(0 for coin,
     *          1 for fireball, 2 for ightning ball, 3 for lettuce ball, and 4 for waterball)
     * @return- return image representation of bullet
     */
    public int bulletType(int i)
    {
        if(i==1)
        {
            return R.drawable.fireball;
        }
        else if(i==2)
        {
            return R.drawable.lightning_ball;
        }
        else if(i==3)
        {
            return R.drawable.lettuceball;
        }
        else if(i==4)
        {
            return R.drawable.waterball;
        }
        else if(i==0)
        {
            return R.drawable.coin;
        }

        return R.drawable.blank_tile;
    }

}
