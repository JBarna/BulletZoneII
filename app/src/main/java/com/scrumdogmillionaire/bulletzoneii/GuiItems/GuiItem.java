package com.scrumdogmillionaire.bulletzoneii.GuiItems;

/**
 * Base class for Objects that are responsible for the visual representation of element on the map
 */
public abstract class GuiItem {
    String display;

    /**
     * Sets the display variable for the GuiItem to the provided string
     * @param s - string that is to be displayed on the screen
     */
    public void setDisplay(String s){display=s;}

    /**
     * returns the visual representation of the element
     * @return - string that is to represent item
     */
    public String getDisplay(){return display;}
}
