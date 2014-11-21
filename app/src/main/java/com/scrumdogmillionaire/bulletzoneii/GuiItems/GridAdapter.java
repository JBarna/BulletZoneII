package com.scrumdogmillionaire.bulletzoneii.GuiItems;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.scrumdogmillionaire.bulletzoneii.LogicItems.MapItem;

/**
 * An adapater class to display a text in a Gridview
 *
 */
public class GridAdapter extends BaseAdapter {

    private GuiItemFactory guiItemFactory;
    private MapItem[] mapItems;
    private Context context;

    /**
     * Constructor
     */
    public GridAdapter(Context context, MapItem[] mapItems) {
        this.context = context;
        this.mapItems = mapItems;
        guiItemFactory = new GuiItemFactory(context);
/*        texts = tmp.clone();
        for(int i=0; i<256; i++)
        {
            texts[i] = tmp[i];
        }*/
    }

    /**
     * Updates the grid of map items
     * @param tmp - array of strings that are to be displayed in the gridview
     */
    public void update( MapItem[] tmp ){
        mapItems = tmp.clone();
    }

    /**
     * Returns How many items are represented by the adapter
     * @return a count of items
     */
    public int getCount() {
        return 256;
    }

    /**
     * Return item at given position in the gridview,e have it return null because we will not be
     * accessing items directly
     * @param position - position of item wished to be return
     * @return - return null because we do not need this methodor now
     */
    public Object getItem(int position) {return null;}

    /**
     * Returns row id of item, but we don't need it so we just have it return 0
     * @param position - position of item wished for Id of
     * @return - just return zero because we will not need this method for now
     */
    public long getItemId(int position) {
        return 0;
    }

    /**
     *Displays data at specified position in grid view
     * @param position - - position of item wished to be displayed
     * @param currView - the view we are going to convert
     * @param vg - parent viewGroup
     * @return - returns view(which is the textview)
     */
    public View getView(int position, View currView, ViewGroup vg) {
        View viewToReturn = null;

        if (currView == null) {
            GuiItem guiItem = guiItemFactory.makeGuiItem(mapItems[position]);
            viewToReturn = guiItem.getDisplay();
        }
        else {
            viewToReturn = currView;
        }


        return viewToReturn;
    }
}
