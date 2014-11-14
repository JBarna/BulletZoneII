package com.scrumdogmillionaire.bulletzoneii.items;

import android.widget.TextView;

/**
 * Created by jeep on 11/13/14.
 */
public class TextFactory{
        public TextGUI makeTextGUI(MapItem item)
        {
            TextGUI ret = new TextGUI("");
            if(item instanceof EmptySpace)
            {
                return new TextGUI(" ");
            }
            else if(item instanceof DestructibleWall)
            {
                return new TextGUI("\u25a0");
            }
            else if(item instanceof IndestructibleWall)
            {
                return new TextGUI("\u25a0");
            }
            else if(item instanceof Bullet)
            {
                return new TextGUI("\u2022");
            }
            else if(item instanceof Tank)
            {
                if(item.getAttribute(2) == 0)
                {
                    return new TextGUI("^");
                }
                else if(item.getAttribute(2) == 2)
                {
                    return new TextGUI(">");
                }
                else if(item.getAttribute(2) == 4)
                {
                    return new TextGUI("v");
                }
                else if(item.getAttribute(2) == 6)
                {
                    return new TextGUI("<");
                }
            }
            return ret;

        }


}
