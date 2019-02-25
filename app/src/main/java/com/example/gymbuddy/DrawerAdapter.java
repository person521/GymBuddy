package com.example.gymbuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DrawerAdapter extends BaseAdapter {

    Context context;
    ArrayList<hamburger> mHamburgers;

    public DrawerAdapter(Context context, ArrayList<hamburger> hamburgers) {
        this.context = context;
        mHamburgers = hamburgers;
    }

    public int getCount() {
        return mHamburgers.size();
    }

    public Object getItem(int position) {
        return mHamburgers.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_items, null);
        }
        else {
            view = convertView;
        }

        TextView titleView = (TextView) view.findViewById(R.id.title);
        TextView subtitleView = (TextView) view.findViewById(R.id.subTitle);
        ImageView iconView = (ImageView) view.findViewById(R.id.icon);

        titleView.setText( mHamburgers.get(position).mTitle);
        subtitleView.setText( mHamburgers.get(position).mSubtitle );
        iconView.setImageResource(mHamburgers.get(position).mIcon);

        return view;
    }
}
