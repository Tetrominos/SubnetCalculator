package com.example.tetrimino.subnetcalculator;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
    private static final String TAG = "MainActivity message";
    static final String STATE_IP = "ip";
    static final String STATE_CIDR = "cidr";
    private String currentIp;
    private int currentCIDR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeInput();

        //restores previous state in case of change
        if(savedInstanceState != null){
            currentIp = savedInstanceState.getString(STATE_IP);
            currentCIDR = savedInstanceState.getInt(STATE_CIDR);
            calculate();
        }

        //goButton saves current state and calls the calculate method, calls hideSoftKeyboard method.
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "The calculate button was pressed");
                currentIp = ipAddressEditText.getText().toString();
                currentCIDR = Integer.parseInt(cidrEditText.getText().toString());
                calculate();
                hideSoftKeyboard(MainActivity.this, v);
            }
        });
    }

    public void calculate(){
        IPAddress ipAddress = new IPAddress(currentIp, currentCIDR);

        String[] calculationData = new String[] {ipAddress.getSubnetId(), ipAddress.getBroadcast(), ipAddress.getFirstHost(), ipAddress.getLastHost()};

        ListAdapter adapter = new SubnetListViewAdapter(this, R.layout.subnet_list_row, calculationData);
        ListView resultListView = (ListView) findViewById(R.id.listView);
        resultListView.setAdapter(adapter);
        ipAddressEditText.clearFocus();
        cidrEditText.clearFocus();
        goButton.requestFocus();
    }

    /**
     * Initializes both input views and the calculate button
     */
    public void initializeInput(){
        ipAddressEditText = (EditText) findViewById(R.id.ipAddress);
        cidrEditText = (EditText) findViewById(R.id.cidr);
        goButton = (Button) findViewById(R.id.goButton);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(STATE_IP, currentIp);
        savedInstanceState.putInt(STATE_CIDR, currentCIDR);

        super.onSaveInstanceState(savedInstanceState);
    }


    /**
     * Hides the virtual keyboard when called
     * @param activity current activity
     * @param view     view passed from the caller method
     */
    public static void hideSoftKeyboard (Activity activity, View view)
    {
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

}

