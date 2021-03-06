package com.example.tetrimino.subnetcalculator;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.example.tetrimino.subnetcalculator.programLogic.IPAddress;

public class MainActivity extends AppCompatActivity {
    private Button goButton;
    private EditText ipAddressEditText, cidrEditText;
    private static final String TAG = "MainActivity message";
    static final String STATE_IP = "ip";
    static final String STATE_CIDR = "cidr";
    private String currentIp;
    private int currentCIDR;
    private String[] mDrawerItems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.main_activity_name);

        initializeInput();

        initializeDrawer();

        //restores previous state in case of change
        if(savedInstanceState != null){
            currentIp = savedInstanceState.getString(STATE_IP);
            currentCIDR = savedInstanceState.getInt(STATE_CIDR);
            if(!(currentIp == null)) {
                calculate();
            }
        }

        //goButton saves current state and calls the calculate method, calls hideSoftKeyboard method.
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Log.d(TAG, "The calculate button was pressed");
                    currentIp = ipAddressEditText.getText().toString();
                    currentCIDR = Integer.parseInt(cidrEditText.getText().toString());
                        calculate();
                    hideSoftKeyboard(MainActivity.this, v);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.invalid_input), Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    public void calculate(){
        IPAddress ipAddress = new IPAddress(currentIp, currentCIDR);

        String[] calculationData = new String[] {ipAddress.getSubnetId(), ipAddress.getBroadcast(), ipAddress.getFirstHost(), ipAddress.getLastHost()};

        ListAdapter adapter = new SubnetListViewAdapter(this, R.layout.subnet_list_row, calculationData);
        ListView resultListView = (ListView) findViewById(R.id.listView);
        resultListView.setAdapter(adapter);
        justifyListViewHeightBasedOnChildren(resultListView);
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

    /**
     * Precalculates ListView, making it possible for the ListView to be used inside a ScrollView
     * Always use after setting the adapter
     * @param listView  Listview that gets the pretedermined height
     */
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

    public void initializeDrawer(){
        mDrawerItems = getResources().getStringArray(R.array.drawer_items);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.d(TAG, "onDrawerClosed: " + getTitle());

                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mDrawerItems));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
            mDrawerLayout.closeDrawer(mDrawerList);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle
        // If it returns true, then it has handled
        // the nav drawer indicator touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {

        switch(position){
            case 0:
                break;
            case 1:
                Intent intent = new Intent(this, PingActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    }

