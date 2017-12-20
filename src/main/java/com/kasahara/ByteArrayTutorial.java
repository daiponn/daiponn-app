package com.kasahara;

public class ByteArrayTutorial{

    public static void main(String[] args){
        //16進数を2進数で表すと以下
        //0x11=00010001, 0x22=00100010, 0x33=00110011, 0x44=01000100
        byte[] packet = {0x11, 0x22, 0x33, 0x44};
        System.out.println(packet[0]);//17
        System.out.println(packet[1]);//34
        System.out.println(packet[2]);//51
        System.out.println(packet[3]);//68

        // 「&」の使い方(両方のbitが1の場合のみ1)
        System.out.println(packet[0] & 0x01);//1   (0x01=00000001)
        System.out.println(packet[0] & 0x10);//16  (0x10=00010000)
        System.out.println(packet[0] & 0x22);//0   (0x22=00100010)
        System.out.println(packet[0] & 0x33);//17  (0x33=01000100)
        System.out.println(packet[0] & 0x0f);//1   (0x0f=00001111)
        System.out.println(packet[0] & 0xf0);//16  (0xf0=11110000)

        // 「|」の使い方

        // 「^」の使い方

        // 「~」の使い方　



        //byteの値を、4bitで区切って逆にしてみる
        // ex) 0x56 → 0x65
        byte data = 0x56;
        // 0x56=01010110, 0x78=01111000

        //まずは、4bitごとに分解してみる
        int front4bit = data & 0xf0;
        int back4bit = data & 0x0f;
        System.out.println(Integer.toHexString(front4bit)); //0x50 (01010000)
        System.out.println(Integer.toHexString(back4bit));  //0x06  (00000110)

        //次にfront4Bitを右に4,back4Bitを左に4ずらす。
        int newback4bit = front4bit >> 4;
        int newfront4bit = back4bit << 4;
        System.out.println(Integer.toHexString(newfront4bit)); //0x60 (01100000)
        System.out.println(Integer.toHexString(newback4bit));  //0x05 (00000101)

        //1が立っているbitを抽出
        int newdata = newfront4bit | newback4bit;
        System.out.println(Integer.toHexString(newdata)); //0x65　逆になってる！


    }

}
