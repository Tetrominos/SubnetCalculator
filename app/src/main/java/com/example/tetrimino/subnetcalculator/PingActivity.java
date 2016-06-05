package com.example.tetrimino.subnetcalculator;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class PingActivity extends AppCompatActivity {
    private TextView testView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);

        initialize();

        //restores previous state in case of change
        if(savedInstanceState != null){

        }
    }

    public void initialize(){
        testView = (TextView) findViewById(R.id.testView);
    }

}
