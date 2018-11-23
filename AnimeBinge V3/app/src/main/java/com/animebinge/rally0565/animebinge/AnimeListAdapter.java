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
    private ArrayList<AnimeShow> arAnime;


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

        ViewHolder holder = new ViewHolder();
        if(convertView == null) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resourceID, parent, false);

            holder.txtName = (TextView) convertView.findViewById(R.id.tvItemName);
            holder.imgAnime = (ImageView) convertView.findViewById(R.id.imgImage);
            convertView.setTag(holder);

            //http://www.androidhub4you.com/2012/09/hello-friends-today-i-am-going-to-share.html
            AnimeShow animeShow = arAnime.get(position);
            holder.txtName.setText(animeShow.getName());
            byte[] bImage = animeShow.getImage();
            ByteArrayInputStream bImageStream = new ByteArrayInputStream(bImage);
            Bitmap bmImage = BitmapFactory.decodeStream(bImageStream);
            holder.imgAnime.setImageBitmap(bmImage);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}
