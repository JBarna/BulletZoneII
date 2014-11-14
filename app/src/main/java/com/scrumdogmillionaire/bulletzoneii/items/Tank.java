package com.scrumdogmillionaire.bulletzoneii.items;

/**
 * Created by jeep on 11/13/14.
 */
public class Tank extends MapItem{
    private int health;
    private int id;
    private int dir;

    public Tank(int i){
        attributes[0] = getId(i);
        attributes[1] = getDir(i);
        attributes[2] = getDir(i);
        type="tank";
    }
    public int getDir(int i){return i% 10;}
    public int getHealth(int i){
       int ret = i/10;
       ret = ret%1000;
       return ret;
    }
    public int getId(int i)
    {
       int ret = i/10000;
       ret=ret%1000;
       return ret;
    }
}

