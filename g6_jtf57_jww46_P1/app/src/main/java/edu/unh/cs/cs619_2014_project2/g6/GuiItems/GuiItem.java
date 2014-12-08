package edu.unh.cs.cs619_2014_project2.g6.GuiItems;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Base class for Objects that are responsible for the visual representation of element on the map
 */
public abstract class GuiItem {
    View display;
    private static Context context;

    /**
     * Sets the display variable for the GuiItem to the provided string
     * @param imageToDisplay - string that is to be displayed on the screen
     */
    protected void setDisplay(int  imageToDisplay){
        //create the text view
        //TextView textview = new TextView(context);
        //textview.setLayoutParams(new GridView.LayoutParams(50, 50));
        ImageView imageView = new ImageView(context);

        //set the text from the item
        //textview.setText(stringToDisplay);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageResource(imageToDisplay);

        display = imageView;
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
