package com.example.tetrimino.subnetcalculator;

import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * Created by dmajc on 22.5.2016..
 * Contains custom View Adapter for the subnet_list_row.xml row layout
 */
public class SubnetListViewAdapter extends ArrayAdapter<String> {

    private final String[] titles = new String[] {"Subnet ID", "Broadcast address", "First host", "Last host"};
    private final String[] data;

    SubnetListViewAdapter(Context context, int textViewResourceId, String[] data){
        super(context, R.layout.subnet_list_row, data);
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater thisInflater = LayoutInflater.from(getContext());
        View rowView = thisInflater.inflate(R.layout.subnet_list_row, parent, false);
        TextView title = (TextView) rowView.findViewById(R.id.title);
        TextView value = (TextView) rowView.findViewById(R.id.value);

        title.setText(titles[position]);
        value.setText(data[position]);

        return rowView;
    }
}
