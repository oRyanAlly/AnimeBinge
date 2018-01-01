package com.animebinge.rally0565.animebinge;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterFactory;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Ryan on 2017-11-29.
 */

public class EpisodeAdapter extends ArrayAdapter<String> {

    Context context;
    String[] episodes;
    int imgID;

    public EpisodeAdapter(Context context, int imgID, String[] episodes) {
        super(context, R.layout.episode_item, episodes);

        this.context = context;
        this.episodes = episodes;
        this.imgID = imgID;
     }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                Activity.LAYOUT_INFLATER_SERVICE);

        View row = layoutInflater.inflate(R.layout.episode_item, null, true);

        ImageView imgArrow =  row.findViewById(R.id.imgArrow);
        TextView tvEpisodeName = row.findViewById(R.id.tvEpisodeName);

       tvEpisodeName.setText(episodes[position]);
       imgArrow.setImageResource(imgID);
        return row;
    }
}