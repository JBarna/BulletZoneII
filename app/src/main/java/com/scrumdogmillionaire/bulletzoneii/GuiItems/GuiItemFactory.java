package com.scrumdogmillionaire.bulletzoneii.GuiItems;

import android.content.Context;

import com.scrumdogmillionaire.bulletzoneii.LogicItems.Bullet;
import com.scrumdogmillionaire.bulletzoneii.LogicItems.DestructibleWall;
import com.scrumdogmillionaire.bulletzoneii.LogicItems.EmptySpace;
import com.scrumdogmillionaire.bulletzoneii.LogicItems.IndestructibleWall;
import com.scrumdogmillionaire.bulletzoneii.LogicItems.MapItem;
import com.scrumdogmillionaire.bulletzoneii.LogicItems.Tank;

/**
 * Factory Class that returns the appropriate GuiItem based on the mapItem that is provided
 */
public class GuiItemFactory {

    public GuiItemFactory(Context context){
        GuiItem.setContext(context);
    }
    /**
     * Returns appropriated GuiItem based on the type of map item that is passed in
     * @param item - mapItem object for an element
     * @return -appropriate GuiItem associated with the mapItem provided
     */
    public GuiItem makeGuiItem(MapItem item)
    {
        GuiItem ret;
        if(item instanceof EmptySpace)
        {
            return new EmptySpaceGui();
        }
        else if(item instanceof DestructibleWall)
        {
            return new DestuctibleWallGui();
        }
        else if(item instanceof IndestructibleWall)
        {
            return new IndestructibleWallGui();
        }
        else if(item instanceof Bullet)
        {
            return new BulletGui();
        }
        else if(item instanceof Tank)
        {
            return new TankGui(item);
        }
        return null;

    }
}
