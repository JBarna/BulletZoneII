package com.scrumdogmillionaire.bulletzoneii.GuiItems;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Base class for Objects that are responsible for the visual representation of element on the map
 */
public abstract class GuiItem {
    View display;
    private static Context context;

    /**
     * Sets the display variable for the GuiItem to the provided string
     * @param stringToDisplay - string that is to be displayed on the screen
     */
    protected void setDisplay(String stringToDisplay){
        //create the text view
        TextView textview = new TextView(context);
        textview.setLayoutParams(new GridView.LayoutParams(50, 50));

        //set the text from the item
        textview.setText(stringToDisplay);

        display = textview;
    }

    /**
     * Used to set the context for the view to be made
     * @param context1
     */
    public static void setContext(Context context1){
        context = context1;
    }

    /**
     * returns the visual representation of the element
     * @return - string that is to represent item
     */
    public View getDisplay(){
        return display;
    }
}
