package com.scrumdogmillionaire.bulletzoneii.GuiItems;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * An adapater class to display a text in a Gridview
 *
 */
public class TextAdapter extends BaseAdapter {

    private Context cont;
    private String[] texts;

    /**
     * Constructor
     * @param context - Context for the the textAdapter
     * @param tmp - array of strings that are to be displayed in the gridview
     */
    public TextAdapter(Context context, String[] tmp) {
        cont = context;
        texts = tmp.clone();
        for(int i=0; i<256; i++)
        {
            texts[i] = tmp[i];
        }
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
        TextView textview;
        if (currView == null) {
            textview = new TextView(cont);
            textview.setLayoutParams(new GridView.LayoutParams(50, 50));
        }
        else {
            textview = (TextView) currView;
        }

        textview.setText(texts[position]);
        return textview;
    }
}
