package com.scrumdogmillionaire.bulletzoneii.GuiItems;

import android.content.Context;

import com.scrumdogmillionaire.bulletzoneii.LogicItems.MapItem;

/**
 *  GuiItem object used to display an tank on the map
 */
public class TankGui extends GuiItem{
    /*
     * Base Constructor
     */
    public TankGui(MapItem tank)
    {
        setDisplay(tankType(tank.getAttribute(MapItem.DIRECTION)));
    }

    /**
     * Returns string to set the display based on the direction of the tank
     * @param i - an integer representing the direction of the tank
     * @return - a string to provide a visual representation of the direction of the tank
     */
    private String tankType(int i)
    {
        if(i == 0)
        {
            return "^";
        }
        else if(i == 2)
        {
            return  ">";
        }
        else if(i == 4)
        {
            return "v";
        }
        else if(i == 6)
        {
            return "<";
        }
        return "";
    }

}