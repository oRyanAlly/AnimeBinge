package com.animebinge.rally0565.animebinge;

import android.app.Activity;
import android.content.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ryan on 2017-10-18.
 */

public class AnimeListAdapter extends BaseAdapter {
    private Context context;
    private String[] animeNames;
    private int[] imageId;

    public AnimeListAdapter(Context context, String[] animeNames, int[] imageId) {
        this.context = context;
        this.animeNames = animeNames;
        this.imageId = imageId;
    }

    @Override
    public int getCount() {

        return animeNames.length;
    }

    @Override
    public Object getItem(int position) {

        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    private class ViewHolder {
        ImageView imgAnime;
        TextView txtName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = new ViewHolder();

        if(row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.griditem_layout, null);

            holder.txtName = (TextView) row.findViewById(R.id.tvItemName);
            holder.imgAnime = (ImageView) row.findViewById(R.id.imgImage);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }
        holder.txtName.setText(animeNames[position]);
        holder.imgAnime.setImageResource(imageId[position]);

        return row;
    }
}
