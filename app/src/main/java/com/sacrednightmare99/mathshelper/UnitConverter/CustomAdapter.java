package com.sacrednightmare99.mathshelper.UnitConverter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.sacrednightmare99.mathshelper.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {

    private final Context mContext;
    private final int mDropDownResource;
    private final List<String> mItems;

    public CustomAdapter(Context context, int resource, int dropDownResource, List<String> items) {
        super(context, resource, items);
        mContext = context;
        mDropDownResource = dropDownResource;
        mItems = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) convertView;
        if (textView == null) {
            textView = new TextView(mContext);
        }
        String item = mItems.get(position);
        textView.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        textView.setText(getAbbreviation(item));
        return textView;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(mDropDownResource, parent, false);
        }
        String item = mItems.get(position);
        TextView textView = view.findViewById(R.id.spinner_text);
        textView.setText(item);
        return view;
    }

    private String getAbbreviation(String item) {
        if ("Kilometers".equals(item)) {
            return "Km";
        } else if ("Meter per second".equals(item)) {
            return "M/s";
        } else if ("Kilometer per hour".equals(item)) {
            return "Km/hr";
        } else if ("Foot per second".equals(item)) {
            return "Foot/s";
        } else if ("Miles per hour".equals(item)) {
            return "Miles/hr";
        } else {
            return item;
        }
    }
}

