package com.example.tetrimino.subnetcalculator.programLogic;

import android.content.Intent;
import android.util.Log;

/**
 * Created by Tetrimino on 1.5.2016..
 * Holds information about the IP address, and methods to manipulate same address and calculate subnet information
 */
public class IPAddress {
    int firstOctet, secondOctet, thirdOctet, fourthOctet, Cidr;
    private static final String TAG = "Calculation result";
    int forSubnetID = 0, forBroadcast = 1, fullOctet = 255;
    public enum AddressClass {
        A, B, C
    }

    AddressClass addressClass;

    public IPAddress(String ipAddress, int cidr){
        IPStringToOctets(ipAddress);
        this.Cidr = cidr;
        setClass(Cidr);
    }

    /**
     * Returns subnet ID adress based on the IP address class type
     * example: 192.168.100.0
     * @return returns subnet ID adress
     */
    public String getSubnetId(){
        switch(addressClass){
            case A:
                return firstOctet + "." + octetChangedTo(octetIntoBinary(secondOctet), Cidr, forSubnetID) + "." + forSubnetID + "." + forSubnetID;

            case B:
                return firstOctet + "." + secondOctet + "." + octetChangedTo(octetIntoBinary(thirdOctet), Cidr, forSubnetID) + "." + forSubnetID;

            case C:
                return firstOctet + "." + secondOctet + "." + thirdOctet + "." + octetChangedTo(octetIntoBinary(fourthOctet), Cidr, forSubnetID);
        }
        return "error";
    }

    /**
     * Returns broadcast address based on the IP address class type
     * example: 192.168.100.255
     * @return returns broadcast address
     */
    public String getBroadcast(){
        switch(addressClass){
            case A:
                return firstOctet + "." + octetChangedTo(octetIntoBinary(secondOctet), Cidr, forBroadcast) + "." + fullOctet + "." + fullOctet;

            case B:
                return firstOctet + "." + secondOctet + "." + octetChangedTo(octetIntoBinary(thirdOctet), Cidr, forBroadcast) + "." + fullOctet;

            case C:
                return firstOctet + "." + secondOctet + "." + thirdOctet + "." + octetChangedTo(octetIntoBinary(fourthOctet), Cidr, forBroadcast);
        }
        return "error";
    }

    /**
     * Returns first host address based on the IP address class type
     * example: 192.168.100.1
     * @return returns first host address
     */
    public String getFirstHost(){
        switch(addressClass){
            case A:
                return firstOctet + "." + octetChangedTo(octetIntoBinary(secondOctet), Cidr, forSubnetID) + "." + forSubnetID + "." + (forSubnetID+1);

            case B:
                return firstOctet + "." + secondOctet + "." + octetChangedTo(octetIntoBinary(thirdOctet), Cidr, forSubnetID) + "." + (forSubnetID+1);

            case C:
                return firstOctet + "." + secondOctet + "." + thirdOctet + "." + (octetChangedTo(octetIntoBinary(fourthOctet), Cidr, forSubnetID)+1);
        }
        return "error";
    }

    /**
     * Returns last host address based on the IP address class type
     * example: 192.168.100.254
     * @return returns last host address
     */
    public String getLastHost() {
        switch(addressClass){
            case A:
                return firstOctet + "." + octetChangedTo(octetIntoBinary(secondOctet), Cidr, forBroadcast) + "." + fullOctet + "." + (fullOctet-1);

            case B:
                return firstOctet + "." + secondOctet + "." + octetChangedTo(octetIntoBinary(thirdOctet), Cidr, forBroadcast) + "." + (fullOctet    -1);

            case C:
                return firstOctet + "." + secondOctet + "." + thirdOctet + "." + (octetChangedTo(octetIntoBinary(fourthOctet), Cidr, forBroadcast)-1);
        }
        return "error";
    }

    /**
     * Turns a decimal number into a binary octet
     * @param octet passed octet in decimal format
     * @return      binary value of octet in string format, 8 bits long
     */
    public String octetIntoBinary(int octet){
        String octetInBinary = Integer.toBinaryString(octet);
        while(octetInBinary.length()<8) {
            octetInBinary = "0" + octetInBinary;
        }
        Log.d(TAG, "The decimal number " + octet + " is " + octetInBinary + " in binary");
        return octetInBinary;
    }

    /**
     * Calculates binary value of a broadcast or subnet ID (based on the BroadOrID parameter) from a binary octet
     * @param octet     original octet
     * @param cidr      cidr, used to calculate how many bits need to be changed
     * @param BroadOrID what the change the bits into
     * @return          returns the broadcast or subnet ID
     */
    public int octetChangedTo(String octet, int cidr, int BroadOrID){
        String changedOctet = octet.substring(0, cidr%8);
        while(changedOctet.length()<8) {
            changedOctet += BroadOrID;
        }
        Log.d(TAG, "The adjusted address in binary is:" + changedOctet);

        return Integer.parseInt(changedOctet, 2);
    }

    /**
     * Sets the class of the address based on the cidr
     * @param cidr  cidr
     */
    public void setClass(int cidr){
        switch(cidr/8){
            case 1:
                addressClass = AddressClass.A;
            case 2:
                addressClass = AddressClass.B;
            case 3:
                addressClass = AddressClass.C;
        }
    }

    /**
     * gives the octet variables values based on the string that was passed to it
     * @param ip IP address in string form
     */
    public void IPStringToOctets(String ip){
        int firstDotIndex, secondDotIndex,thirdDotIndex;
        firstDotIndex = ip.indexOf(".");
        secondDotIndex = ip.indexOf(".", firstDotIndex + 1);
        thirdDotIndex = ip.indexOf(".", secondDotIndex + 1);
        firstOctet = Integer.parseInt(ip.substring(0, firstDotIndex));
        secondOctet = Integer.parseInt(ip.substring(firstDotIndex + 1, secondDotIndex));
        thirdOctet = Integer.parseInt(ip.substring(secondDotIndex + 1, thirdDotIndex));
        fourthOctet = Integer.parseInt(ip.substring(thirdDotIndex + 1));
    }
}


