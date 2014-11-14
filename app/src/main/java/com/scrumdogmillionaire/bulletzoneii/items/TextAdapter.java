package com.scrumdogmillionaire.bulletzoneii.items;

import android.widget.TextView;

/**
 * Created by jeep on 11/13/14.
 */
public class TextAdapter extends MapItem{
        MapItem item;
        public TextAdapter(MapItem it)
        {
            item = it;
        }

        @Override
        public String getType()
        {
            if(item.getType().equals("empty"))
            {
                return " ";
            }
            else if(item.getType().equals("destructible"))
            {
                return "\u25a0";
            }
            else if(item.getType().equals("indestructible"))
            {
                return "\u25a0";
            }
            else if(item.getType().equals("bullet"))
            {
                return "\u2022";
            }
            else if(item.getType().equals("tank"))
            {
                if(item.getAttribute() == 0)
                {
                    return "^";
                }
                else if(item.getAttribute() == 2)
                {
                    return ">";
                }
                else if(item.getAttribute() == 4)
                {
                    return "v";
                }
                else if(item.getAttribute() == 6)
                {
                    return "<";
                }
            }
            return "";

        }


}
