package edu.unh.cs.cs619_2014_project2.g6.GuiItems;

import android.content.Context;

import edu.unh.cs.cs619_2014_project2.g6.LogicItems.IndestructibleWall;
import edu.unh.cs.cs619_2014_project2.g6.R;

/**
 *  GuiItem object used to display an emptySpace on the map
 */
public class IndestructibleWallGui extends GuiItem{
    /**
     * Base Constructor
     */
    public IndestructibleWallGui()
    {
        setDisplay(R.drawable.indestructable_wall);
    }
}
