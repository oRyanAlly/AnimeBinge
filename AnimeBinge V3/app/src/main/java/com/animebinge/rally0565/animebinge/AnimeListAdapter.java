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

import java.io.ByteArrayInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Ryan on 2017-10-18.
 */

public class AnimeListAdapter extends ArrayAdapter<AnimeShow> {
    private Context context;
    private int resourceID;
    private ArrayList<AnimeShow> arAnime = new ArrayList<AnimeShow>();

    public AnimeListAdapter(Context context, int resourceID, ArrayList<AnimeShow> anime) {
        super(context, resourceID, anime);
        this.context = context;
        this.resourceID = resourceID;
        this.arAnime = anime;
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
        //http://www.androidhub4you.com/2012/09/hello-friends-today-i-am-going-to-share.html
        AnimeShow animeShow = arAnime.get(position);
        holder.txtName.setText(animeShow.getName());
        byte[] bImage = animeShow.getImage();
        ByteArrayInputStream bImageStream = new ByteArrayInputStream(bImage);
        Bitmap bmImage = BitmapFactory.decodeStream(bImageStream);
        holder.imgAnime.setImageBitmap(bmImage);
        return row;
    }
}
