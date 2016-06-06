package com.example.tetrimino.subnetcalculator;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
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
    static final String STATE_HOST = "host";
    private String[] mDrawerItems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);

        initialize();

        initializeDrawer();

        if(savedInstanceState != null){
            try {
                PingFormatter pingFormatter = new PingFormatter(savedInstanceState.getString(STATE_HOST));
                pingFormatter.UsePingFunction();
                pingFormatter.toPingObject();
                pingsToCustomListView(pingFormatter.getArrayOfPings());
            } catch (Exception e){
                e.getMessage();
            }
        }

        pingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PingFormatter pingFormatter = new PingFormatter(addressEditText.getText().toString());
                    pingFormatter.UsePingFunction();
                    pingFormatter.toPingObject();
                    pingsToCustomListView(pingFormatter.getArrayOfPings());
                    addressEditText.clearFocus();
                    pingButton.requestFocus();
                } catch (Exception e){
                    e.getMessage();
                }
            }
        });
        //restores previous state in case of change

    }

    public void initialize(){
        addressEditText = (EditText) findViewById(R.id.address);
        pingButton = (Button) findViewById(R.id.pingButton);
    }

    public void pingsToCustomListView(ArrayList<PingObject> pingObjectList){
        ListAdapter adapter = new PingListViewAdapter(this, R.layout.ping_list_row, makePingObjectArray(pingObjectList));
        ListView resultListView = (ListView) findViewById(R.id.listView);
        resultListView.setAdapter(adapter);
        justifyListViewHeightBasedOnChildren(resultListView);
    }

    public void justifyListViewHeightBasedOnChildren (ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        if (adapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(par);
        listView.requestLayout();
    }

    public PingObject[] makePingObjectArray(ArrayList<PingObject> x){
        PingObject[] pingArray = new PingObject[x.size()];
        pingArray = x.toArray(pingArray);
        return pingArray;
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(STATE_HOST, addressEditText.getText().toString());

        super.onSaveInstanceState(savedInstanceState);
    }

    public void initializeDrawer(){
        mDrawerItems = getResources().getStringArray(R.array.drawer_items);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mDrawerItems));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
            mDrawerLayout.closeDrawer(mDrawerList);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {

        switch(position){
            case 0:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case 1:
                break;
            default:
                break;
        }

    }

    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

}
