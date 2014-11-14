package com.scrumdogmillionaire.bulletzoneii.items;

/**
 * Created by jeep on 11/12/14.
 */
public abstract class MapItem{
    String type;
    int attributes[] = {0,0,0};

    public void setType(String s){type=s;}
    public String getType(){return type;}

    //public void setAttribute(int i, int val){attributes[i]=val;}
    public int getAttribute(int i){return attributes[i];}
}