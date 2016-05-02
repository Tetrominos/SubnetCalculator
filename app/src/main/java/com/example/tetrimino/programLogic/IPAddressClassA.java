package com.example.tetrimino.programLogic;

/**
 * Created by Tetrimino on 1.5.2016..
 * Holds the methods for calculating the subnet ID, the broadcast address and the first and last host addresses
 */
public class IPAddressClassA extends IPAddress {
    public IPAddressClassA(int first, int second, int third, int fourth, int cidr){
        super(first, second, third, fourth, cidr);
        whichCase = cidr/8;
    }

    public String getSubnetId(){
        String octetInBinary, octetBeginning;
        int finalOctet;
        switch(whichCase){
            case 1:
                    octetInBinary = Integer.toBinaryString(secondOctet);
                    octetBeginning = octetInBinary.substring(0, Cidr%8-1);
                    for(int i = 0; i<(8 - octetInBinary.length()); i++)
                        octetInBinary = "0" + octetInBinary;

                    for(int i = 0; i<(8-Cidr%8); i++){
                        octetBeginning+="0";
                    }

                    finalOctet = Integer.parseInt(octetBeginning, 2);
                    return firstOctet + "." + finalOctet + "." + 0 + "." + 0;

            case 2: octetInBinary = Integer.toBinaryString(thirdOctet);
                    octetBeginning = octetInBinary.substring(0, Cidr%8-1);
                    for(int i = 0; i<(8 - octetInBinary.length()); i++)
                        octetInBinary = "0" + octetInBinary;

                    for(int i = 0; i<(8-Cidr%8); i++){
                        octetBeginning+="0";
                    }

                    finalOctet = Integer.parseInt(octetBeginning, 2);

                    return firstOctet + "." + secondOctet + "." + finalOctet + "." + 0;

            case 3: octetInBinary = Integer.toBinaryString(fourthOctet);
                for(int i = 0; i<(8 - octetInBinary.length()); i++)
                    octetInBinary = "0" + octetInBinary;
                    octetBeginning = octetInBinary.substring(0, Cidr%8-1);


                    for(int i = 0; i<(8-Cidr%8); i++){
                        octetBeginning+="0";
                    }

                    finalOctet = Integer.parseInt(octetBeginning, 2);
                    return firstOctet + "." + secondOctet + "." + thirdOctet + "." + finalOctet;
        }
        return "error";
    }

    public String getBroadcast(){
        String octetInBinary, octetBeginning;
        int finalOctet;
        switch(whichCase){
            case 1:
                octetInBinary = Integer.toBinaryString(secondOctet);
                octetBeginning = octetInBinary.substring(0, Cidr%8-1);
                for(int i = 0; i<(8 - octetInBinary.length()); i++)
                    octetInBinary = "0" + octetInBinary;

                for(int i = 0; i<(8-Cidr%8); i++){
                    octetBeginning+="1";
                }

                finalOctet = Integer.parseInt(octetBeginning, 2);
                return firstOctet + "." + finalOctet + "." + 255 + "." + 255;

            case 2: octetInBinary = Integer.toBinaryString(thirdOctet);
                octetBeginning = octetInBinary.substring(0, Cidr%8-1);
                for(int i = 0; i<(8 - octetInBinary.length()); i++)
                    octetInBinary = "0" + octetInBinary;

                for(int i = 0; i<(8-Cidr%8); i++){
                    octetBeginning+="1";
                }

                finalOctet = Integer.parseInt(octetBeginning, 2);

                return firstOctet + "." + secondOctet + "." + finalOctet + "." + 255;

            case 3: octetInBinary = Integer.toBinaryString(fourthOctet);
                octetBeginning = octetInBinary.substring(0, Cidr%8-1);
                for(int i = 0; i<(8 - octetInBinary.length()); i++)
                    octetInBinary = "0" + octetInBinary;

                for(int i = 0; i<(8-Cidr%8); i++){
                    octetBeginning+="1";
                }

                finalOctet = Integer.parseInt(octetBeginning, 2);
                return firstOctet + "." + secondOctet + "." + thirdOctet + "." + finalOctet;
        }
        return "error";
    }

    public String getFirstHost(){
        String octetInBinary, octetBeginning;
        int finalOctet;
        switch(whichCase){
            case 1:
                octetInBinary = Integer.toBinaryString(secondOctet);
                octetBeginning = octetInBinary.substring(0, Cidr%8-1);
                for(int i = 0; i<(8 - octetInBinary.length()); i++)
                    octetInBinary = "0" + octetInBinary;

                for(int i = 0; i<(8-Cidr%8); i++){
                    octetBeginning+="0";
                }

                finalOctet = Integer.parseInt(octetBeginning, 2);
                return firstOctet + "." + finalOctet + "." + 0 + "." + 1;

            case 2: octetInBinary = Integer.toBinaryString(thirdOctet);
                octetBeginning = octetInBinary.substring(0, Cidr%8-1);
                for(int i = 0; i<(8 - octetInBinary.length()); i++)
                    octetInBinary = "0" + octetInBinary;

                for(int i = 0; i<(8-Cidr%8); i++){
                    octetBeginning+="0";
                }

                finalOctet = Integer.parseInt(octetBeginning, 2);

                return firstOctet + "." + secondOctet + "." + finalOctet + "." + 1;

            case 3: octetInBinary = Integer.toBinaryString(fourthOctet);
                octetBeginning = octetInBinary.substring(0, Cidr%8-1);
                for(int i = 0; i<(8 - octetInBinary.length()); i++)
                    octetInBinary = "0" + octetInBinary;

                for(int i = 0; i<(8-Cidr%8); i++){
                    octetBeginning+="0";
                }

                finalOctet = Integer.parseInt(octetBeginning, 2) + 1;
                return firstOctet + "." + secondOctet + "." + thirdOctet + "." + finalOctet;
        }
        return "error";
    }

    public String getLastHost(){
        String octetInBinary, octetBeginning;
        int finalOctet;
        switch(whichCase){
            case 1:
                octetInBinary = Integer.toBinaryString(secondOctet);
                octetBeginning = octetInBinary.substring(0, Cidr%8-1);
                for(int i = 0; i<(8 - octetInBinary.length()); i++)
                    octetInBinary = "0" + octetInBinary;

                for(int i = 0; i<(8-Cidr%8); i++){
                    octetBeginning+="1";
                }

                finalOctet = Integer.parseInt(octetBeginning, 2);
                return firstOctet + "." + finalOctet + "." + 255 + "." + 254;

            case 2: octetInBinary = Integer.toBinaryString(thirdOctet);
                octetBeginning = octetInBinary.substring(0, Cidr%8-1);
                for(int i = 0; i<(8 - octetInBinary.length()); i++)
                    octetInBinary = "0" + octetInBinary;

                for(int i = 0; i<(8-Cidr%8); i++){
                    octetBeginning+="1";
                }

                finalOctet = Integer.parseInt(octetBeginning, 2);

                return firstOctet + "." + secondOctet + "." + finalOctet + "." + 254;

            case 3: octetInBinary = Integer.toBinaryString(fourthOctet);
                octetBeginning = octetInBinary.substring(0, Cidr%8-1);
                for(int i = 0; i<(8 - octetInBinary.length()); i++)
                    octetInBinary = "0" + octetInBinary;

                for(int i = 0; i<(8-Cidr%8); i++){
                    octetBeginning+="1";
                }

                finalOctet = Integer.parseInt(octetBeginning, 2) - 1;
                return firstOctet + "." + secondOctet + "." + thirdOctet + "." + finalOctet;
        }
        return "error";
    }
}
