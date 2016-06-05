package com.example.tetrimino.subnetcalculator.programLogic;

import java.util.ArrayList;

import static com.example.tetrimino.subnetcalculator.programLogic.Ping.ping;

/**
 * Created by dmajc on 5.6.2016..
 */
public class PingFormatter extends Thread {
    String host;
    public static final int NUMBER_OF_PINGS = 4;
    ArrayList<String> arrayOfPingResults = new ArrayList<String>();
    public PingFormatter(String host){
        this.host = host;
    }

    public void UsePingFunction(){
        for(int i = 0; i < NUMBER_OF_PINGS; i++){
            try {
                arrayOfPingResults.add(ping(host));
            } catch (Exception e){
                e.getStackTrace();
            }
        }
    }


}
