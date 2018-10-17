package com.animebinge.rally0565.animebinge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;

public class IntroAdapter extends PagerAdapter{
    Context context;
    LayoutInflater layoutInflater;

    public IntroAdapter(Context context) {
        this.context = context;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
