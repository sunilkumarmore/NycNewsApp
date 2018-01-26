package com.example.sunilm.nycnewsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sunilm on 1/23/2018.
 */

public class ListItem extends LinearLayout {
    public TextView first;
    public ImageView iv;
    public ListItem(Context context) {
        super(context);
        inflateXML(context);
    }
    private void inflateXML(Context context) {
        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.list_item, this);
        this.first = (TextView) findViewById(R.id.tv1);
        this.iv = (ImageView) findViewById(R.id.imageViewFonyc);
    }
}