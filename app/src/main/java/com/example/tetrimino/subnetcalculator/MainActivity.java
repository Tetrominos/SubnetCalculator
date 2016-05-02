package com.example.tetrimino.subnetcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.example.tetrimino.subnetcalculator.programLogic.IPAddressClassA;

public class MainActivity extends AppCompatActivity {
    private Button goButton;
    private EditText firstOctetText, secondOctetText, thirdOctetText, fourthOctetText, cidrText;
    private TextView subnetID, broadcast, firstHost, lastHost;
    private static final String TAG = "Ja sam poruka";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeInput();
        initializeTextViews();

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
        IPAddressClassA ipAddress = new IPAddressClassA(Integer.parseInt(firstOctetText.getText().toString()),
                                                    Integer.parseInt(secondOctetText.getText().toString()),
                                                    Integer.parseInt(thirdOctetText.getText().toString()),
                                                    Integer.parseInt(fourthOctetText.getText().toString()),
                                                    Integer.parseInt(cidrText.getText().toString()));

        subnetID.setText(ipAddress.getSubnetId());
        broadcast.setText(ipAddress.getBroadcast());
        firstHost.setText(ipAddress.getFirstHost());
        lastHost.setText(ipAddress.getLastHost());
    }

    public void initializeInput(){
        firstOctetText = (EditText) findViewById(R.id.firstOctet);
        secondOctetText = (EditText) findViewById(R.id.secondOctet);
        thirdOctetText = (EditText) findViewById(R.id.thirdOctet);
        fourthOctetText = (EditText) findViewById(R.id.fourthOctet);
        cidrText = (EditText) findViewById(R.id.CIDR);
    }

    public void initializeTextViews(){
        subnetID = (TextView) findViewById(R.id.subnetID);
        broadcast = (TextView) findViewById(R.id.broadcast);
        firstHost = (TextView) findViewById(R.id.firstHost);
        lastHost = (TextView) findViewById(R.id.lastHost);
    }
}
