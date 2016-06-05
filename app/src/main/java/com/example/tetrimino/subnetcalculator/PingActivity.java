package com.example.tetrimino.subnetcalculator;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tetrimino.subnetcalculator.programLogic.PingFormatter;
import com.example.tetrimino.subnetcalculator.programLogic.PingObject;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.tetrimino.subnetcalculator.programLogic.Ping.ping;

public class PingActivity extends AppCompatActivity {
    private TextView neki;
    private EditText addressEditText;
    private Button pingButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);

        initialize();

        pingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PingFormatter pingFormatter = new PingFormatter(addressEditText.getText().toString());
                    pingFormatter.UsePingFunction();
                    pingFormatter.toPingObject();
                    pingsToCustomListView(pingFormatter.getArrayOfPings());
                } catch (Exception e){
                    e.getMessage();
                }
            }
        });
        //restores previous state in case of change
        if(savedInstanceState != null){

        }
    }

    public void initialize(){
        neki = (TextView) findViewById(R.id.testView);
        addressEditText = (EditText) findViewById(R.id.address);
        pingButton = (Button) findViewById(R.id.pingButton);
    }

    public void pingsToCustomListView(ArrayList<PingObject> pingObjectList){
        
    }

}
