package com.scrumdogmillionaire.bulletzoneii.items;

/**
 * Created by jeep on 11/13/14.
 */
public class TextGUI {
    private String display;
    public TextGUI(String val)
    {
        setDisplay(val);
    }
    public void setDisplay(String s)
    {
        display=s;
    }
    public String getDisplay()
    {
        return display;
    }
}
