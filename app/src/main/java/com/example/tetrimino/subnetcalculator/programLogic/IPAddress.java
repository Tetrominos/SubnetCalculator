package com.example.tetrimino.subnetcalculator.programLogic;

/**
 * Created by Tetrimino on 1.5.2016..
 */
public abstract class IPAddress {
    int firstOctet, secondOctet, thirdOctet, fourthOctet, Cidr, whichCase;

    public IPAddress(int first, int second, int third, int fourth, int cidr){
        firstOctet = first;
        secondOctet = second;
        thirdOctet = third;
        fourthOctet = fourth;
        Cidr = cidr;
    }
}
