package edu.unh.cs.cs619_2014_project2.g6.GuiItems;

import android.content.Context;

import edu.unh.cs.cs619_2014_project2.g6.R;

/**
 * GuiItem object used to display a Destructible wall item on the map
 */
public class DestuctibleWallGui extends GuiItem{
    /**
     * Base Constructor
     * setsDisplay to image representation of destructible wall
     */
    public DestuctibleWallGui()
    {
        setDisplay(R.drawable.wall);
    }
}
