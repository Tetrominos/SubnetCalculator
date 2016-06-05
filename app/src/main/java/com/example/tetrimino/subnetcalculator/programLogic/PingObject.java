package com.example.tetrimino.subnetcalculator.programLogic;

/**
 * Created by dmajc on 5.6.2016..
 */
public class PingObject {
    private int sizeInBytes;
    private int ttl;
    private int time;
    private int sequence;
    private String address;

    public PingObject (int sizeInBytes, int ttl, int time, int sequence, String address){
        this.sizeInBytes = sizeInBytes;
        this.ttl = ttl;
        this.time = time;
        this.sequence = sequence;
        this.address = address;
    }

    public int getSizeInBytes() {
        return sizeInBytes;
    }

    public void setSizeInBytes(int sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
