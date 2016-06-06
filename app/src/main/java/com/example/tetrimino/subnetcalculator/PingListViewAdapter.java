package com.example.tetrimino.subnetcalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tetrimino.subnetcalculator.programLogic.PingObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmajc on 6.6.2016..
 */
public class PingListViewAdapter extends ArrayAdapter<PingObject> {

    //private final String[] titles = new String[] {"Subnet ID", "Broadcast address", "First host", "Last host"};
    private final PingObject[] data;

    public PingListViewAdapter(Context context, int textViewResourceId, PingObject[] data){
        super(context, R.layout.ping_list_row, data);
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater thisInflater = LayoutInflater.from(getContext());
        View rowView = thisInflater.inflate(R.layout.ping_list_row, parent, false);
        TextView hostView = (TextView) rowView.findViewById(R.id.host);
        TextView byteView = (TextView) rowView.findViewById(R.id.bytesValue);
        TextView ttlView = (TextView) rowView.findViewById(R.id.ttlValue);
        TextView seqView = (TextView) rowView.findViewById(R.id.seqValue);
        TextView timeView = (TextView) rowView.findViewById(R.id.timeValue);

        TextView byteLabel = (TextView) rowView.findViewById(R.id.bytesLabel);
        TextView ttlLabel = (TextView) rowView.findViewById(R.id.ttlLabel);
        TextView seqLabel = (TextView) rowView.findViewById(R.id.seqLabel);

        hostView.setText(data[position].getAddress());
        byteView.setText(String.valueOf(data[position].getSizeInBytes()));
        ttlView.setText(String.valueOf(data[position].getTtl()));
        seqView.setText(String.valueOf(data[position].getSequence()));
        timeView.setText(String.valueOf(data[position].getTime()) + "ms");

        return rowView;
    }
}
