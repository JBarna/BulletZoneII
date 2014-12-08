package edu.unh.cs.cs619_2014_project2.g6.GuiItems;

import android.content.Context;

import edu.unh.cs.cs619_2014_project2.g6.LogicItems.MapItem;
import edu.unh.cs.cs619_2014_project2.g6.R;

/**
 *  GuiItem object used to display an tank on the map
 */
public class TankGui extends GuiItem{
    /**
     * Base constructor
     * sets the display to the correct image representation of tank
     * @param tank - MapItem object for the tank
     */
    public TankGui(MapItem tank)
    {
        setDisplay(tankType(tank.getAttribute(MapItem.DIRECTION),
                tank.getAttribute(MapItem.TYPE), tank.getAttribute(MapItem.HEALTH)));
    }

    /**
     * Returns appropriate tank gui item based on the, tank type, character, and direction
     * @param i - the integer representation of the direction of the tank
     * @param j - the integer that tells whether a tank is friendly or the enemy
     * @param h - the integer that tells what character to represent(0 = charmander, 1 = pikachu
     *          , 3 = bulbasaur, 4 = squirtle)
     * @return - returns the integer for the appropriate image object
     */
    private int tankType(int i, int j, int h)
    {
        String string = "pikachu_up_3";
        if(j==1){
            if(h==3) {
                if (i == 0) {
                    return R.drawable.charmander_up_3;
                } else if (i == 2) {
                    return R.drawable.charmander_right_3;
                } else if (i == 4) {
                    return R.drawable.charmander_down_3;
                } else if (i == 6) {
                    return R.drawable.charmander_left_3;
                }
            }
            else if(h==2){
                if (i == 0) {
                    return R.drawable.charmander_up_2;
                } else if (i == 2) {
                    return R.drawable.charmander_right_2;
                } else if (i == 4) {
                    return R.drawable.charmander_down_2;
                } else if (i == 6) {
                    return R.drawable.charmander_left_2;
                }
            }
            else if(h==1)
            {
                if (i == 0) {
                    return R.drawable.charmander_up_1;
                } else if (i == 2) {
                    return R.drawable.charmander_right_1;
                } else if (i == 4) {
                    return R.drawable.charmander_down_1;
                } else if (i == 6) {
                    return R.drawable.charmander_left_1;
                }
            }
        }
        else if(j==2) {
            if(h==3) {
                if (i == 0) {
                    return R.drawable.pikachu_up_3;
                } else if (i == 2) {
                    return R.drawable.pikachu_right_3;
                } else if (i == 4) {
                    return R.drawable.pikachu_down_3;
                } else if (i == 6) {
                    return R.drawable.pikachu_left_3;
                }
           }
            else if(h==2){
                if (i == 0) {
                    return R.drawable.pikachu_up_2;
                } else if (i == 2) {
                    return R.drawable.pikachu_right_2;
                } else if (i == 4) {
                    return R.drawable.pikachu_down_2;
                } else if (i == 6) {
                    return R.drawable.pikachu_left_2;
                }
            }
            else if(h==1)
            {
                if (i == 0) {
                    return R.drawable.pikachu_up_1;
                } else if (i == 2) {
                    return R.drawable.pikachu_right_1;
                } else if (i == 4) {
                    return R.drawable.pikachu_down_1;
                } else if (i == 6) {
                    return R.drawable.pikachu_left_1;
                }
            }
        }
        else if(j==3) {
            if(h==3) {
                if (i == 0) {
                    return R.drawable.bulbasaur_up_3;
                } else if (i == 2) {
                    return R.drawable.bulbasaur_right_3;
                } else if (i == 4) {
                    return R.drawable.bulbasaur_down_3;
                } else if (i == 6) {
                    return R.drawable.bulbasaur_left_3;
                }
            }
            else if(h==2){
                if (i == 0) {
                    return R.drawable.bulbasaur_up_2;
                } else if (i == 2) {
                    return R.drawable.bulbasaur_right_2;
                } else if (i == 4) {
                    return R.drawable.bulbasaur_down_2;
                } else if (i == 6) {
                    return R.drawable.bulbasaur_left_2;
                }
            }
            else if(h==1)
            {
                if (i == 0) {
                    return R.drawable.bulbasaur_up_1;
                } else if (i == 2) {
                    return R.drawable.bulbasaur_right_1;
                } else if (i == 4) {
                    return R.drawable.bulbasaur_down_1;
                } else if (i == 6) {
                    return R.drawable.bulbasaur_left_1;
                }
            }
        }
        else if(j==4)
        {
            if(h==3) {
                if (i == 0) {
                    return R.drawable.squirtle_up_3;
                } else if (i == 2) {
                    return R.drawable.squirtle_right_3;
                } else if (i == 4) {
                    return R.drawable.squirtle_down_3;
                } else if (i == 6) {
                    return R.drawable.squirtle_left_3;
                }
            }
            else if(h==2){
                if (i == 0) {
                    return R.drawable.squirtle_up_2;
                } else if (i == 2) {
                    return R.drawable.squirtle_right_2;
                } else if (i == 4) {
                    return R.drawable.squirtle_down_2;
                } else if (i == 6) {
                    return R.drawable.squirtle_left_2;
                }
            }
            else if(h==1)
            {
                if (i == 0) {
                    return R.drawable.squirtle_up_1;
                } else if (i == 2) {
                    return R.drawable.squirtle_right_1;
                } else if (i == 4) {
                    return R.drawable.squirtle_down_1;
                } else if (i == 6) {
                    return R.drawable.squirtle_left_1;
                }
            }
        }
        else if(j==0)
        {
            if(h==3) {
                if (i == 0) {
                    return R.drawable.meowth_up_3;
                } else if (i == 2) {
                    return R.drawable.meowth_right_3;
                } else if (i == 4) {
                    return R.drawable.meowth_down_3;
                } else if (i == 6) {
                    return R.drawable.meowth_left_3;
                }
            }
            else if(h==2){
                if (i == 0) {
                    return R.drawable.meowth_up_2;
                } else if (i == 2) {
                    return R.drawable.meowth_right_2;
                } else if (i == 4) {
                    return R.drawable.meowth_down_2;
                } else if (i == 6) {
                    return R.drawable.meowth_left_2;
                }
            }
            else if(h==1)
            {
                if (i == 0) {
                    return R.drawable.meowth_up_1;
                } else if (i == 2) {
                    return R.drawable.meowth_right_1;
                } else if (i == 4) {
                    return R.drawable.meowth_down_1;
                } else if (i == 6) {
                    return R.drawable.meowth_left_1;
                }
            }
        }
        return R.drawable.blank_tile;
    }



}