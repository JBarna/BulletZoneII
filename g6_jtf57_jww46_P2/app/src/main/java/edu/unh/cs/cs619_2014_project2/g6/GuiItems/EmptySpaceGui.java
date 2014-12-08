package edu.unh.cs.cs619_2014_project2.g6.GuiItems;

import android.content.Context;
import android.view.View;

import edu.unh.cs.cs619_2014_project2.g6.R;

/**
 * GuiItem object used to display an emptySpace on the map
 */
public class EmptySpaceGui extends GuiItem{
    /**
     * Base Constructor
     * sets display to image representation of Emptyspace
     */
    public EmptySpaceGui()
    {
        setDisplay(R.drawable.blank);
    }
}
