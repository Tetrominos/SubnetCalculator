package com.example.tetrimino.subnetcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.example.tetrimino.subnetcalculator.programLogic.IPAddress;

public class MainActivity extends AppCompatActivity {
    private Button goButton;
    private EditText ipAddressEditText, cidrEditText;
    private TextView subnetID, broadcast, firstHost, lastHost;
    private static final String TAG = "Ja sam poruka";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeInput();
        //initializeTextViews();

        goButton = (Button) findViewById(R.id.goButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "shit has happened");
                Toast.makeText(v.getContext(), "You clicked the button", Toast.LENGTH_LONG).show();
                stuffHappens();

            }
        });
    }

    public void stuffHappens(){
        IPAddress ipAddress = new IPAddress(ipAddressEditText.getText().toString(), Integer.parseInt(cidrEditText.getText().toString()));

        String[] calculationData = new String[] {ipAddress.getSubnetId(), ipAddress.getBroadcast(), ipAddress.getFirstHost(), ipAddress.getLastHost()};

        ListAdapter adapter = new SubnetListViewAdapter(this, R.layout.subnet_list_row, calculationData);
        ListView resultListView = (ListView) findViewById(R.id.listView);
        resultListView.setAdapter(adapter);
        /*subnetID.setText(ipAddress.getSubnetId());
        broadcast.setText(ipAddress.getBroadcast());
        firstHost.setText(ipAddress.getFirstHost());
        lastHost.setText(ipAddress.getLastHost());*/
    }

    public void initializeInput(){
        ipAddressEditText = (EditText) findViewById(R.id.ipAddress);
        cidrEditText = (EditText) findViewById(R.id.cidr);
    }

    /*public void initializeTextViews(){
        subnetID = (TextView) findViewById(R.id.subnetID);
        broadcast = (TextView) findViewById(R.id.broadcast);
        firstHost = (TextView) findViewById(R.id.firstHost);
        lastHost = (TextView) findViewById(R.id.lastHost);
    }*/
}
