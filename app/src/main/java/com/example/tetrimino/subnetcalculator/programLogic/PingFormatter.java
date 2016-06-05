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
    int time, ttl;
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
            arrayOfPings.add(new PingObject(SIZE_IN_BYTES, setTtl(i), setTime(i), tempSequenceNum, setAddress(i)));
        }
    }

    public String setAddress(int i){
        return arrayOfPingResults.get(i).substring(arrayOfPingResults.get(i).indexOf("from "), arrayOfPingResults.get(i).indexOf(": icmp_seq"));
    }

    public int setTtl(int i){
        return Integer.parseInt(arrayOfPingResults.get(i).substring(arrayOfPingResults.get(i).indexOf("ttl="), arrayOfPingResults.get(i).indexOf(" time")));
    }

    public int setTime(int i){
        return Integer.parseInt(arrayOfPingResults.get(i).substring(arrayOfPingResults.get(i).indexOf("time="), arrayOfPingResults.get(i).indexOf(" ms")));
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

