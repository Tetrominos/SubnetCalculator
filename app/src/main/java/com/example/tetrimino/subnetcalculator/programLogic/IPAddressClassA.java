package com.example.tetrimino.subnetcalculator.programLogic;

import android.util.Log;

/**
 * Created by Tetrimino on 1.5.2016..
 * Holds the methods for calculating the subnet ID, the broadcast address and the first and last host addresses
 */
public class IPAddressClassA extends IPAddress {
    private static final String TAG = "Calculation result";
    int forSubnetID = 0, forBroadcast = 1, fullOctet = 255;

    public IPAddressClassA(int first, int second, int third, int fourth, int cidr){
        super(first, second, third, fourth, cidr);
        whichCase = cidr/8;
    }

    public String getSubnetId(){
        switch(whichCase){
            case 1:
                return firstOctet + "." + octetChangedTo(octetIntoBinary(secondOctet), Cidr, forSubnetID) + "." + forSubnetID + "." + forSubnetID;

            case 2:
                return firstOctet + "." + secondOctet + "." + octetChangedTo(octetIntoBinary(thirdOctet), Cidr, forSubnetID) + "." + forSubnetID;

            case 3:
                return firstOctet + "." + secondOctet + "." + thirdOctet + "." + octetChangedTo(octetIntoBinary(fourthOctet), Cidr, forSubnetID);
        }
        return "error";
    }

    public String getBroadcast(){
        switch(whichCase){
            case 1:
                return firstOctet + "." + octetChangedTo(octetIntoBinary(secondOctet), Cidr, forBroadcast) + "." + fullOctet + "." + fullOctet;

            case 2:
                return firstOctet + "." + secondOctet + "." + octetChangedTo(octetIntoBinary(thirdOctet), Cidr, forBroadcast) + "." + fullOctet;

            case 3:
                return firstOctet + "." + secondOctet + "." + thirdOctet + "." + octetChangedTo(octetIntoBinary(fourthOctet), Cidr, forBroadcast);
        }
        return "error";
    }

    public String getFirstHost(){
        switch(whichCase){
            case 1:
                return firstOctet + "." + octetChangedTo(octetIntoBinary(secondOctet), Cidr, forSubnetID) + "." + forSubnetID + "." + (forSubnetID+1);

            case 2:
                return firstOctet + "." + secondOctet + "." + octetChangedTo(octetIntoBinary(thirdOctet), Cidr, forSubnetID) + "." + (forSubnetID+1);

            case 3:
                return firstOctet + "." + secondOctet + "." + thirdOctet + "." + (octetChangedTo(octetIntoBinary(fourthOctet), Cidr, forSubnetID)+1);
        }
        return "error";
    }

    public String getLastHost() {
        switch(whichCase){
            case 1:
                return firstOctet + "." + octetChangedTo(octetIntoBinary(secondOctet), Cidr, forBroadcast) + "." + fullOctet + "." + (fullOctet-1);

            case 2:
                return firstOctet + "." + secondOctet + "." + octetChangedTo(octetIntoBinary(thirdOctet), Cidr, forBroadcast) + "." + (fullOctet    -1);

            case 3:
                return firstOctet + "." + secondOctet + "." + thirdOctet + "." + (octetChangedTo(octetIntoBinary(fourthOctet), Cidr, forBroadcast)-1);
        }
        return "error";
    }

    public String octetIntoBinary(int octet){
        String octetInBinary = Integer.toBinaryString(octet);
        while(octetInBinary.length()<8) {
            octetInBinary = "0" + octetInBinary;
        }
        Log.d(TAG, "The decimal number " + octet + " is " + octetInBinary + " in binary");
        return octetInBinary;
    }

    public int octetChangedTo(String octet,int cidr, int BroadOrID){
        String changedOctet = octet.substring(0, cidr%8);
        while(changedOctet.length()<8) {
            changedOctet += BroadOrID;
        }
        Log.d(TAG, "The adjusted address in binary is:" + changedOctet);

        return Integer.parseInt(changedOctet, 2);
    }
}