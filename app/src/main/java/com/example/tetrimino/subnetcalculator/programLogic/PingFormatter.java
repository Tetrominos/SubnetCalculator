package com.example.tetrimino.subnetcalculator.programLogic;

import android.os.AsyncTask;
import android.util.Log;

import com.example.tetrimino.subnetcalculator.PingActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.example.tetrimino.subnetcalculator.programLogic.Ping.ping;

/**
 * Created by dmajc on 5.6.2016..
 */
public class PingFormatter {
    String host, tempResult = null, address;
    int ttl;
    double time;
    public static final int NUMBER_OF_PINGS = 3, SIZE_IN_BYTES = 64, FIRST_PRIO = 10, SECOND_PRIO = 9, THIRD_PRIO = 8;
    ArrayList<String> arrayOfPingResults = new ArrayList<String>();
    ArrayList<PingObject> arrayOfPings = new ArrayList<PingObject>();

    public PingFormatter(String host){
        this.host = host;
    }

    public void UsePingFunction(){
        PingThread t = new PingThread();
        t.start();
        try {
            t.join();
        } catch(InterruptedException e){
            e.getStackTrace();
        }
    }

    public void toPingObject(){
        int tempSequenceNum = 0;
        for(int i = 0; i < NUMBER_OF_PINGS; i++){
            tempSequenceNum = i+1;
            PingObject x = new PingObject(SIZE_IN_BYTES, setTtl(i), setTime(i), tempSequenceNum, setAddress(i));
            arrayOfPings.add(x);
        }
    }

    public String setAddress(int i){
        address = arrayOfPingResults.get(i).substring(arrayOfPingResults.get(i).indexOf("from ")+5, arrayOfPingResults.get(i).indexOf(": icmp_seq"));
        return address;
    }

    public int setTtl(int i){
        ttl = Integer.parseInt(arrayOfPingResults.get(i).substring(arrayOfPingResults.get(i).indexOf("ttl=")+4, arrayOfPingResults.get(i).indexOf(" time")));
        return ttl;
    }

    public double setTime(int i){
        String tempString = arrayOfPingResults.get(i).substring(arrayOfPingResults.get(i).indexOf("time=")+5, arrayOfPingResults.get(i).indexOf(" ms"));
        String tempString2 = arrayOfPingResults.get(i).substring(arrayOfPingResults.get(i).indexOf("time=")+5, arrayOfPingResults.get(i).indexOf(" ms"));

        time = Double.parseDouble(arrayOfPingResults.get(i).substring(arrayOfPingResults.get(i).indexOf("time=")+5, arrayOfPingResults.get(i).indexOf(" ms")));
        return time;
    }

    public ArrayList<PingObject> getArrayOfPings(){
        return arrayOfPings;
    }

    class PingThread extends Thread{
        @Override
        public void run() {
            for(int i = 0; i < NUMBER_OF_PINGS; i++) {
                try {
                    tempResult = ping(host);
                    Log.d("pinganje u threadu", tempResult);
                    arrayOfPingResults.add(tempResult);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
    }

}

