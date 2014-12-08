package edu.unh.cs.cs619_2014_project2.g6.GuiItems;

import android.content.Context;

import edu.unh.cs.cs619_2014_project2.g6.LogicItems.Bullet;
import edu.unh.cs.cs619_2014_project2.g6.LogicItems.DestructibleWall;
import edu.unh.cs.cs619_2014_project2.g6.LogicItems.EmptySpace;
import edu.unh.cs.cs619_2014_project2.g6.LogicItems.IndestructibleWall;
import edu.unh.cs.cs619_2014_project2.g6.LogicItems.MapItem;
import edu.unh.cs.cs619_2014_project2.g6.LogicItems.Tank;

/**
 * Factory Class that returns the appropriate GuiItem based on the mapItem that is provided
 */
public class GuiItemFactory {
    private EmptySpaceGui emptySpaceGui;
    private IndestructibleWallGui indestructibleWallGui;

    /**
     * Constructor
     * sets the context, initializes the emptySpaceGui, and inistializes the indestructableWallGui
     * @param context
     */
    public GuiItemFactory(Context context){
        GuiItem.setContext(context);
        emptySpaceGui = new EmptySpaceGui();
        indestructibleWallGui = new IndestructibleWallGui();
    }
    /**
     * Returns appropriated GuiItem based on the type of map item that is passed in
     * @param item - mapItem object for an element
     * @return -appropriate GuiItem associated with the mapItem provided
     */
    public GuiItem makeGuiItem(MapItem item)
    {

        if(item instanceof EmptySpace)
            {
               // return emptySpaceGui;
                return new EmptySpaceGui();
            }
            else if(item instanceof DestructibleWall)
            {
                return new DestuctibleWallGui();
            }
            else if(item instanceof IndestructibleWall)
            {
               // return indestructibleWallGui;
                return new IndestructibleWallGui();
            }
            else if(item instanceof Bullet)
            {
                return new BulletGui(item);
            }
            else if(item instanceof Tank)
            {
                return new TankGui(item);
            }
            return null;

        }
}
