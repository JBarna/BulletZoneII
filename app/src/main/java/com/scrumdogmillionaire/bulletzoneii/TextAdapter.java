package com.scrumdogmillionaire.bulletzoneii;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by jeep on 11/13/14.
 */
public class TextAdapter extends BaseAdapter {

    private Context cont;
    private String[] texts;

    public TextAdapter(Context context, String[] tmp) {
        cont = context;
        texts = tmp.clone();
        for(int i=0; i<256; i++)
        {
            texts[i] = tmp[i];
        }
    }

    public int getCount() {
        return 256;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv;
        if (convertView == null) {
            tv = new TextView(cont);
            tv.setLayoutParams(new GridView.LayoutParams(50, 50));
        }
        else {
            tv = (TextView) convertView;
        }

        tv.setText(texts[position]);
        return tv;
    }
}
